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
import zw.co.vokers.zoledge.adapters.AccountQuotaAdapter;
import zw.co.vokers.zoledge.models.AccountQuotaModel;
import zw.co.vokers.zoledge.utils.FixedGridLayoutManager;

public class AccountQuotasFrag extends Fragment {
    int scrollX = 0;

    List<AccountQuotaModel> accountQuotaModelList = new ArrayList<>();

    private Context mContext;

    RecyclerView rvClub;

    HorizontalScrollView headerScroll;

    SearchView searchView;

    AccountQuotaAdapter clubAdapter;

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
        View layout = inflater.inflate(R.layout.account_quotas, container, false);
        //ListView listView = (ListView) layout.findViewById(android.R.id.list);
        //VinceTextView emptyTextView = (VinceTextView) layout.findViewById(android.R.id.empty);
        //listView.setEmptyView(emptyTextView);

        rvClub = layout.findViewById(R.id.rvClubQuotas);
        headerScroll = layout.findViewById(R.id.headerScrollQuotas);

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
        accountQuotaModelList.add(new AccountQuotaModel("ZOLSPOT Packages", "ZOLSPOT", "1","200MB","0","0","0"));
        accountQuotaModelList.add(new AccountQuotaModel("Basic Essentials", "BB_HOME_BASIC", "3","500MB","1","0","0"));
        accountQuotaModelList.add(new AccountQuotaModel("Family Essentials", "BB_HOME_STARTER", "5","1GB","1","1","0"));
        accountQuotaModelList.add(new AccountQuotaModel("Family Entertainment", "BB_HOME_FAST", "7","1.5GB","1","1","0"));
        accountQuotaModelList.add(new AccountQuotaModel("Modern Family", "BB_HOME_FASTER", "10","2GB","1","1","0"));
        accountQuotaModelList.add(new AccountQuotaModel("Power Pack", "BB_HOME_FASTEST", "15","3GB","1","1","0"));
        accountQuotaModelList.add(new AccountQuotaModel("Turbo Pack", "BB_HOME_TURBO", "20","4GB","1","1","0"));
        accountQuotaModelList.add(new AccountQuotaModel("Micro-Office", "BB_BUSINESS_ESSENTIALS", "5","1GB","1","1","1"));
        accountQuotaModelList.add(new AccountQuotaModel("Small Office", "BB_BUSINESS_FAST", "15","3GB","1","1","1"));
        accountQuotaModelList.add(new AccountQuotaModel("Office", "BB_BUSINESS_FASTER", "25","10GB","1","1","1"));
        accountQuotaModelList.add(new AccountQuotaModel("Large Office", "BB_BUSINESS_FASTEST", "50","25GB","1","1","1"));
    }

    private void setUpRecyclerView()
    {
        clubAdapter = new AccountQuotaAdapter(mContext, accountQuotaModelList);

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


