package zw.co.vokers.zoledge.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import zw.co.vokers.zoledge.db.DbBackend;
import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.models.Product;
import zw.co.vokers.zoledge.models.ProductModel;
import zw.co.vokers.zoledge.utils.RecyclerViewFastScroller;
import zw.co.vokers.zoledge.utils.SpacesItemDecoration;
import zw.co.vokers.zoledge.utils.VinceTextView;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener  {

    RecyclerView recyclerView;
    Main2Activity.RecyclerviewAdapter myRecAdapter;
    List<ProductModel> list;
    String searchString="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();

        DbBackend dbBackend = new DbBackend(this);

        ArrayList<Product> items = dbBackend.getProductsList();

        for (Product product : items) {
            list.add(new ProductModel(product, product, product));
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        myRecAdapter = new Main2Activity.RecyclerviewAdapter(list, Main2Activity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
        recyclerView.setAdapter(myRecAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_cardview);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        RecyclerViewFastScroller fastScroller = (RecyclerViewFastScroller) findViewById(R.id.fastscroller);
        fastScroller.setRecyclerView(recyclerView);
        fastScroller.setViewsToUse(R.layout.recycler_view_fast_scroller__fast_scroller, R.id.fastscroller_bubble, R.id.fastscroller_handle);

        final String[] tips = {"When troubleshooting, remember to start with the physical connection, then logical connection.",
                "Remember to put a Service offering, when escalating in Service Now",
                "Remember to check if ALL your tickets are resolved",
                "Remember to check ALL your tickets that are 'In Progress'",
                "Don't forget to use the corporate greeting and closure, during a call",
                "The additional $5, is split into two i.e. $3.50 ONT Rental + $1.50 ONT Insurance"};



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int random = (int) ((Math.random()*6));
                Snackbar snackbar = Snackbar
                        .make(view, tips[random], 5000)
                        .setAction(null, null);
                snackbar.setActionTextColor(Color.CYAN);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));//change Snackbar's background color;
                TextView textView = (TextView)snackbarView .findViewById(android.support.design.R.id.snackbar_text);
                //textView.setText(tips[random]);
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));//change Snackbar's text color;
                snackbar.show(); // Don’t forget to show!

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<ProductModel> filteredModelList = filter(list, newText);
        if (filteredModelList.size() > 0) {
            myRecAdapter.animateTo(filteredModelList);
            myRecAdapter.setFilter(filteredModelList);
            recyclerView.scrollToPosition(0);
            return true;
        } else {
            Toast.makeText(Main2Activity.this, "Product Not Found.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchitem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchitem);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        TextView searchText = (TextView)
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        searchText.setTextColor(Color.parseColor("#FFFFFF"));
        searchText.setHintTextColor(Color.parseColor("#FFFFFF"));
        searchText.setHint("Search for product...");
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_compinfo) {
            startActivity(new Intent(Main2Activity.this, MissionVisionActivity.class));
        }else if (id == R.id.nav_scan) {
        startActivity(new Intent(Main2Activity.this, ScanActivity.class));
        }else if (id == R.id.nav_qr) {
        startActivity(new Intent(Main2Activity.this, WifiScanner.class));
        } else if (id == R.id.nav_fdesk) {
        startActivity(new Intent(Main2Activity.this, FrontDeskSupportActivity.class));
        } else if (id == R.id.nav_tro_fibro) {
            startActivity(new Intent(Main2Activity.this, TroubleFibroniks.class));
        } else if (id == R.id.nav_tro_mpls) {
            startActivity(new Intent(Main2Activity.this, TroubleMpls.class));
        }else if (id == R.id.nav_tro_wimax) {
            startActivity(new Intent(Main2Activity.this, TroubleWimax.class));
        }else if (id == R.id.nav_tro_vsat) {
            startActivity(new Intent(Main2Activity.this, TroubleVsat.class));
        }else if (id == R.id.nav_tro_email) {
        startActivity(new Intent(Main2Activity.this, EmailsTrouble.class));
        }else if (id == R.id.nav_tro_dns) {
        startActivity(new Intent(Main2Activity.this,TroubleDNSActivity.class));
        }else if (id == R.id.nav_zol_self_quiz) {
        startActivity(new Intent(Main2Activity.this, QuizActivity.class));
        }else if (id == R.id.nav_zol_directory) {
        startActivity(new Intent(Main2Activity.this, ZolDirectory.class));
        }else if (id == R.id.nav_call_centre_manual) {
        startActivity(new Intent(Main2Activity.this, CallCentreManual.class));
        }else if (id == R.id.nav_other_banking) {
        startActivity(new Intent(Main2Activity.this, PaymentMethods.class));
        }else if (id == R.id.nav_other_fup) {
        startActivity(new Intent(Main2Activity.this, ZOLFupActivity.class));
        }else if (id == R.id.nav_pilot_alpa) {
        startActivity(new Intent(Main2Activity.this, PilotsAlphabet.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class RecyclerviewAdapter extends RecyclerView.Adapter<Main2Activity.RecyclerviewAdapter.VH>  implements RecyclerViewFastScroller.BubbleTextGetter {

        public List<ProductModel> patientList;
        public Context context;
        ArrayList<ProductModel> mModel;

        public RecyclerviewAdapter(List<ProductModel> parkingList, Context context) {
            this.patientList = parkingList;
            this.context = context;
        }

        @Override
        public Main2Activity.RecyclerviewAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Main2Activity.RecyclerviewAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false));
        }

        @Override
        public void onBindViewHolder(Main2Activity.RecyclerviewAdapter.VH holder, int position) {
            final ProductModel model = patientList.get(position);

            //holder.contactName.setText(model.getmProductSummary().getProductSummary());
            //holder.contactDept.setText(model.getmProductCategory().getProductInfo());
            holder.prodName.setText(model.getmProductName().getProductName());
            holder.prodSummary.setText(model.getmProductSummary().getProductSummary());
            //holder.contactDept.setText(model.getmNatId().getProductCategory());

            ProductModel txt = patientList.get(position);
            String paname =  txt.getmProductName().getProductName().toLowerCase(Locale.getDefault());
            String pasummary =  txt.getmProductSummary().getProductSummary().toLowerCase(Locale.getDefault());

            if (paname.contains(searchString)) {

                int startPos = paname.indexOf(searchString);
                int endPos = startPos + searchString.length();

                Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.prodName.getText());
                spanString.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                holder.prodName.setText(spanString);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetails.class);
                    intent.putExtra("pname", model.getmProductName().getProductName());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return patientList.size();
        }

        @Override
        public String getTextToShowInBubble(final int pos) {
            Character ch = patientList.get(pos).getmProductName().getProductName().charAt(0);
            String s = Character.toString(ch);
            return s/*Character.toString(items.get(pos).charAt(0))*/;
        }

        public void animateTo(List<ProductModel> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<ProductModel> newModels) {
            for (int i = patientList.size() - 1; i >= 0; i--) {
                final ProductModel model = patientList.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<ProductModel> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final ProductModel model = newModels.get(i);
                if (!patientList.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<ProductModel> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final ProductModel model = newModels.get(toPosition);
                final int fromPosition = patientList.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public ProductModel removeItem(int position) {
            final ProductModel model = patientList.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, ProductModel model) {
            patientList.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final ProductModel model = patientList.remove(fromPosition);
            patientList.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        public class VH extends RecyclerView.ViewHolder {
            VinceTextView prodName, prodSummary;

            public VH(View itemView) {
                super(itemView);

                prodName = (VinceTextView) itemView.findViewById(R.id.textView);
                prodSummary = (VinceTextView) itemView.findViewById(R.id.textView2);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = prodName.getText().toString();
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();

                    }
                });
            }

        }

        public void setFilter(List<ProductModel> countryModels) {
            mModel = new ArrayList<>();
            mModel.addAll(countryModels);
            notifyDataSetChanged();
        }

    }

    // Search Method
    private List<ProductModel> filter(List<ProductModel> models, String query) {

        query = query.toLowerCase();
        this.searchString=query;

        final List<ProductModel> filteredModelList = new ArrayList<>();
        for (ProductModel model : models) {
            final String text =  model.getmProductName().getProductName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        myRecAdapter = new Main2Activity.RecyclerviewAdapter(filteredModelList, Main2Activity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
        recyclerView.setAdapter(myRecAdapter);
        myRecAdapter.notifyDataSetChanged();

        return filteredModelList;
    }


}
