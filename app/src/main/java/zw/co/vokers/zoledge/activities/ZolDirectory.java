package zw.co.vokers.zoledge.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.db.DbBackend;
import zw.co.vokers.zoledge.models.Contact;
import zw.co.vokers.zoledge.models.ContactModel;
import zw.co.vokers.zoledge.utils.RecyclerViewFastScroller;
import zw.co.vokers.zoledge.utils.SpacesItemDecoration;
import zw.co.vokers.zoledge.utils.VinceTextView;

/**
 * Created by VinceGee on 3/24/2018.
 */

public class ZolDirectory extends AppCompatActivity implements SearchView.OnQueryTextListener  {

    RecyclerView recyclerView;
    ZolDirectory.RecyclerviewAdapter myRecAdapter;
    List<ContactModel> list;
    String searchString="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);
        //Toolbar toolbar = findViewById(R.id.directory_toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = new ArrayList<>();

        DbBackend dbBackend = new DbBackend(this);

        ArrayList<Contact> items = dbBackend.getContactsList();

        for (Contact contact : items) {
            list.add(new ContactModel(contact, contact, contact,contact));
        }

        recyclerView = (RecyclerView) findViewById(R.id.contactsRecyclerview);
        recyclerView.setHasFixedSize(true);
        myRecAdapter = new ZolDirectory.RecyclerviewAdapter(list, ZolDirectory.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ZolDirectory.this));
        recyclerView.setAdapter(myRecAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_cardview);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        RecyclerViewFastScroller fastScroller = (RecyclerViewFastScroller) findViewById(R.id.contactsFastscroller);
        fastScroller.setRecyclerView(recyclerView);
        fastScroller.setViewsToUse(R.layout.recycler_view_fast_scroller__fast_scroller, R.id.fastscroller_bubble, R.id.fastscroller_handle);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<ContactModel> filteredModelList = filter(list, newText);
        if (filteredModelList.size() > 0) {
            myRecAdapter.animateTo(filteredModelList);
            myRecAdapter.setFilter(filteredModelList);
            recyclerView.scrollToPosition(0);
            return true;
        } else {
            Toast.makeText(ZolDirectory.this, "Contact not found.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
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
        searchText.setHint("Search for contact...");
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    public class RecyclerviewAdapter extends RecyclerView.Adapter<ZolDirectory.RecyclerviewAdapter.VH>  implements RecyclerViewFastScroller.BubbleTextGetter {

        public List<ContactModel> contactsList;
        public Context context;
        ArrayList<ContactModel> mModel;

        public RecyclerviewAdapter(List<ContactModel> parkingList, Context context) {
            this.contactsList = parkingList;
            this.context = context;
        }

        @Override
        public ZolDirectory.RecyclerviewAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ZolDirectory.RecyclerviewAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ZolDirectory.RecyclerviewAdapter.VH holder, int position) {
            final ContactModel model = contactsList.get(position);

            holder.contactName.setText(model.getmContactName().getContactName());
            holder.contactDept.setText(model.getmContactDepartment().getDepartment());
            holder.contactCell.setText(model.getmContactCell().getCellNumber());
            holder.contactExtension.setText(model.getmContactExtension().getExtension());

            ContactModel txt = contactsList.get(position);
            String paname =  txt.getmContactName().getContactName().toLowerCase(Locale.getDefault());
           // String exten =  txt.getmContactExtension().getExtension().toLowerCase(Locale.getDefault());
            //String searchWord = paname + exten;
            if (paname.contains(searchString)) {

                int startPos = paname.indexOf(searchString);
                int endPos = startPos + searchString.length();

                Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.contactName.getText());
                //Spannable spanString1 = Spannable.Factory.getInstance().newSpannable(holder.contactExtension.getText());
                spanString.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //spanString1.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                holder.contactName.setText(spanString);
                //holder.contactExtension.setText(spanString1);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(context, ProductDetails.class);
                    //intent.putExtra("pname", model.getmProductName().getProductName());
                    //context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return contactsList.size();
        }

        @Override
        public String getTextToShowInBubble(final int pos) {
            Character ch = contactsList.get(pos).getmContactName().getContactName().charAt(0);
            String s = Character.toString(ch);
            return s/*Character.toString(items.get(pos).charAt(0))*/;
        }

        public void animateTo(List<ContactModel> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<ContactModel> newModels) {
            for (int i = contactsList.size() - 1; i >= 0; i--) {
                final ContactModel model = contactsList.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<ContactModel> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final ContactModel model = newModels.get(i);
                if (!contactsList.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<ContactModel> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final ContactModel model = newModels.get(toPosition);
                final int fromPosition = contactsList.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public ContactModel removeItem(int position) {
            final ContactModel model = contactsList.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, ContactModel model) {
            contactsList.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final ContactModel model = contactsList.remove(fromPosition);
            contactsList.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        public class VH extends RecyclerView.ViewHolder {
            VinceTextView contactName, contactDept,contactCell,contactExtension;

            public VH(View itemView) {
                super(itemView);

                contactName = (VinceTextView) itemView.findViewById(R.id.contactName);
                contactDept = (VinceTextView) itemView.findViewById(R.id.contactDept);
                contactCell = (VinceTextView) itemView.findViewById(R.id.contactCell);
                contactExtension = (VinceTextView) itemView.findViewById(R.id.contactExtension);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = contactName.getText().toString();
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();

                    }
                });
            }

        }

        public void setFilter(List<ContactModel> countryModels) {
            mModel = new ArrayList<>();
            mModel.addAll(countryModels);
            notifyDataSetChanged();
        }

    }

    // Search Method
    private List<ContactModel> filter(List<ContactModel> models, String query) {

        query = query.toLowerCase();
        this.searchString=query;

        final List<ContactModel> filteredModelList = new ArrayList<>();
        for (ContactModel model : models) {
            final String text =  model.getmContactName().getContactName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        myRecAdapter = new ZolDirectory.RecyclerviewAdapter(filteredModelList, ZolDirectory.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ZolDirectory.this));
        recyclerView.setAdapter(myRecAdapter);
        myRecAdapter.notifyDataSetChanged();

        return filteredModelList;
    }


}

