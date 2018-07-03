package zw.co.vokers.zoledge.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.activities.BaseStationDetails;
import zw.co.vokers.zoledge.activities.ProductDetails;
import zw.co.vokers.zoledge.db.DbBackend;
import zw.co.vokers.zoledge.models.Basestation;
import zw.co.vokers.zoledge.models.BasestationModel;
import zw.co.vokers.zoledge.utils.RecyclerViewFastScroller;
import zw.co.vokers.zoledge.utils.SpacesItemDecoration;
import zw.co.vokers.zoledge.utils.VinceTextView;

public class BaseStationsFragment extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
    RecyclerView recyclerView;
    List<BasestationModel> list;
    String searchString="";
    RecyclerviewAdapter myRecAdapter;
        List<String> mAllValues;
private ArrayAdapter<String> mAdapter;
private Context mContext;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
        }

@Override
public void onDetach() {
        super.onDetach();
        }

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.search_fragment, container, false);
        //ListView listView = (ListView) layout.findViewById(android.R.id.list);
        //VinceTextView emptyTextView = (VinceTextView) layout.findViewById(android.R.id.empty);
        //listView.setEmptyView(emptyTextView);

    list = new ArrayList<>();

    DbBackend dbBackend = new DbBackend(mContext);

    ArrayList<Basestation> items = dbBackend.getBaseStationsList();

    for (Basestation bs : items) {
        list.add(new BasestationModel(bs, bs, bs, bs));
    }

    recyclerView = (RecyclerView) layout.findViewById(R.id.bs_recyclerview);
    recyclerView.setHasFixedSize(true);
    myRecAdapter = new RecyclerviewAdapter(list, mContext);
    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    recyclerView.setAdapter(myRecAdapter);
    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_cardview);
    recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

    RecyclerViewFastScroller fastScroller = (RecyclerViewFastScroller) layout.findViewById(R.id.bs_fastscroller);
    fastScroller.setRecyclerView(recyclerView);
    fastScroller.setViewsToUse(R.layout.recycler_view_fast_scroller__fast_scroller, R.id.fastscroller_bubble, R.id.fastscroller_handle);

    return layout;
        }

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
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

@Override
public boolean onQueryTextSubmit(String query) {
        return false;
        }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<BasestationModel> filteredModelList = filter(list, newText);
        if (filteredModelList.size() > 0) {
            myRecAdapter.animateTo(filteredModelList);
            myRecAdapter.setFilter(filteredModelList);
            recyclerView.scrollToPosition(0);
            return true;
        } else {
            Toast.makeText(getActivity(), "Base Station Not Found.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

/*public void resetSearch() {
        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mAllValues);
        setListAdapter(mAdapter);
        }*/

@Override
public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
        }

@Override
public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
        }

public interface OnItem1SelectedListener {
    void OnItem1SelectedListener(String item);
}


    public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.VH>  implements RecyclerViewFastScroller.BubbleTextGetter {

        public List<BasestationModel> patientList;
        public Context context;
        ArrayList<BasestationModel> mModel;

        public RecyclerviewAdapter(List<BasestationModel> parkingList, Context context) {
            this.patientList = parkingList;
            this.context = context;
        }

        @Override
        public RecyclerviewAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerviewAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerviewAdapter.VH holder, int position) {
            final BasestationModel model = patientList.get(position);

            //holder.contactName.setText(model.getmProductSummary().getProductSummary());
            //holder.contactDept.setText(model.getmProductCategory().getProductInfo());
            holder.bsName.setText(model.getmSiteName().getSiteName());
            holder.bsID.setText(model.getmBsID().getBsID());
            //holder.contactDept.setText(model.getmNatId().getProductCategory());

            BasestationModel txt = patientList.get(position);
            String paname =  txt.getmSiteName().getSiteName().toLowerCase(Locale.getDefault());
            String pasummary =  txt.getmBsID().getBsID().toLowerCase(Locale.getDefault());

            if (pasummary.contains(searchString)) {

                int startPos = pasummary.indexOf(searchString);
                int endPos = startPos + searchString.length();

                Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.bsID.getText());
                spanString.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                holder.bsID.setText(spanString);
            }

            if (paname.contains(searchString)) {

                int startPos = paname.indexOf(searchString);
                int endPos = startPos + searchString.length();

                Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.bsName.getText());
                spanString.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                holder.bsName.setText(spanString);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BaseStationDetails.class);
                    intent.putExtra("siteName", model.getmSiteName().getSiteName());
                    intent.putExtra("bsid", model.getmBsID().getBsID());
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
            Character ch = patientList.get(pos).getmSiteName().getSiteName().charAt(0);
            String s = Character.toString(ch);
            return s/*Character.toString(items.get(pos).charAt(0))*/;
        }

        public void animateTo(List<BasestationModel> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<BasestationModel> newModels) {
            for (int i = patientList.size() - 1; i >= 0; i--) {
                final BasestationModel model = patientList.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<BasestationModel> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final BasestationModel model = newModels.get(i);
                if (!patientList.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<BasestationModel> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final BasestationModel model = newModels.get(toPosition);
                final int fromPosition = patientList.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public BasestationModel removeItem(int position) {
            final BasestationModel model = patientList.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, BasestationModel model) {
            patientList.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final BasestationModel model = patientList.remove(fromPosition);
            patientList.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        public class VH extends RecyclerView.ViewHolder {
            VinceTextView bsName, bsID;

            public VH(View itemView) {
                super(itemView);

                bsName = (VinceTextView) itemView.findViewById(R.id.textView);
                bsID = (VinceTextView) itemView.findViewById(R.id.textView2);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = bsName.getText().toString();
                        Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();

                    }
                });
            }

        }

        public void setFilter(List<BasestationModel> countryModels) {
            mModel = new ArrayList<>();
            mModel.addAll(countryModels);
            notifyDataSetChanged();
        }

    }

    // Search Method
    private List<BasestationModel> filter(List<BasestationModel> models, String query) {

        query = query.toLowerCase();
        this.searchString=query;

        final List<BasestationModel> filteredModelList = new ArrayList<>();
        for (BasestationModel model : models) {
            final String text =  model.getmSiteName().getSiteName().toLowerCase();
            final String bsid =  model.getmBsID().getBsID().toLowerCase();
            if (text.contains(query) || bsid.contains(query)) {
                filteredModelList.add(model);
            }
        }
        myRecAdapter = new RecyclerviewAdapter(filteredModelList, mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myRecAdapter);
        myRecAdapter.notifyDataSetChanged();

        return filteredModelList;
    }
}
