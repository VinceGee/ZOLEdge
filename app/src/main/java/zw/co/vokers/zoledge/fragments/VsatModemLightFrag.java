package zw.co.vokers.zoledge.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.adapters.ClubAdapter;
import zw.co.vokers.zoledge.models.VsatModem;
import zw.co.vokers.zoledge.utils.FixedGridLayoutManager;

public class VsatModemLightFrag extends Fragment{
    int scrollX = 0;

    List<VsatModem> vsatModemList = new ArrayList<>();

    private Context mContext;

    RecyclerView rvClub;

    HorizontalScrollView headerScroll;

    SearchView searchView;

    ClubAdapter clubAdapter;

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
        View layout = inflater.inflate(R.layout.vsat_lights, container, false);
        //ListView listView = (ListView) layout.findViewById(android.R.id.list);
        //VinceTextView emptyTextView = (VinceTextView) layout.findViewById(android.R.id.empty);
        //listView.setEmptyView(emptyTextView);

        rvClub = layout.findViewById(R.id.rvClub);
        headerScroll = layout.findViewById(R.id.headerScroll);

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
        vsatModemList.add(new VsatModem("Front Panel", R.drawable.light5, "Check that power cord is securely connected to power supply (24v), Power Led must be illuminated .Power supply is correctly connected to the rear of the modem"));
        vsatModemList.add(new VsatModem("Rear Panel", R.drawable.light6, "This is how the cables should be connected at the back."));
        vsatModemList.add(new VsatModem("LAN LED", R.drawable.light3, "Illuminated Led will signify connection to the modem via Ethernet cable"));
        vsatModemList.add(new VsatModem("LAN Traffic", R.drawable.light7, "If LAN traffic is present the Led will Blink rapidly"));
        vsatModemList.add(new VsatModem("Alert LED", R.drawable.light1, "If the Alert Led is illuminated, the modem status is reporting an error, you will need to log into the modem and check the status error to be able to troubleshoot further, this is not possible remotely , and will need to be done on site ."));
        vsatModemList.add(new VsatModem("RX / TX Led", R.drawable.light2, "RX Led - if illuminated this will signify that site modem is receiving traffic/data. whereas TX Led – if illuminated this will signify that traffic data is being sent ."));
        vsatModemList.add(new VsatModem("TX Led", R.drawable.light4, "if this remains off and the LAN Led is also off , The connection via Ethernet cable is disconnected or the connecting device is not powered ."));
    }

    private void setUpRecyclerView()
    {
        clubAdapter = new ClubAdapter(mContext, vsatModemList);

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

