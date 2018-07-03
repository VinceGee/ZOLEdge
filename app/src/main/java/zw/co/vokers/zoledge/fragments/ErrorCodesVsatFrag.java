package zw.co.vokers.zoledge.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.adapters.ErrorCodesAdapter;
import zw.co.vokers.zoledge.models.ErrorModel;
import zw.co.vokers.zoledge.utils.FixedGridLayoutManager;

public class ErrorCodesVsatFrag extends Fragment {
    int scrollX = 0;

    List<ErrorModel> errorModelList = new ArrayList<>();

    private Context mContext;

    RecyclerView rvClub;

    HorizontalScrollView headerScroll;

    SearchView searchView;

    ErrorCodesAdapter clubAdapter;

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
        View layout = inflater.inflate(R.layout.error_codes, container, false);
        //ListView listView = (ListView) layout.findViewById(android.R.id.list);
        //VinceTextView emptyTextView = (VinceTextView) layout.findViewById(android.R.id.empty);
        //listView.setEmptyView(emptyTextView);

        rvClub = layout.findViewById(R.id.rvClubError);
        headerScroll = layout.findViewById(R.id.headerScrollError);

        prepareClubData();

        setUpRecyclerView();

        rvClub.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                scrollX += dx;

                headerScroll.scrollTo(scrollX, 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        return layout;
    }

    private void prepareClubData()
    {
        errorModelList.add(new ErrorModel("000", "No connectivity with modem", "The modem has no connectivity with satellite network"));
        errorModelList.add(new ErrorModel("010", "Web interface eror info", "Error in handling of the RX signal"));

    }

    private void setUpRecyclerView()
    {
        clubAdapter = new ErrorCodesAdapter(mContext, errorModelList);

        FixedGridLayoutManager manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        rvClub.setLayoutManager(manager);
        rvClub.setAdapter(clubAdapter);
        rvClub.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                // filter recycler view when query submitted
                clubAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query)
            {
                // filter recycler view when text is changed
                clubAdapter.getFilter().filter(query);
                return false;
            }
        });
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
