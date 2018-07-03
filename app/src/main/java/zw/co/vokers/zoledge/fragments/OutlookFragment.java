package zw.co.vokers.zoledge.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.utils.MetaballMenu;

public class OutlookFragment extends Fragment implements MetaballMenu.MetaballMenuClickListener {

    public static final String ITEM_TEXT = "text_del_item";


    private Context context;
    private View rootView;
    private Button callBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(context).inflate(R.layout.outlook_setup, container, false);
        //callBtn = (Button) rootView.findViewById(R.id.button);
        ((MetaballMenu)rootView.findViewById(R.id.outlook_metaball_menu)).setMenuClickListener(this);


        Outlook2007Fragment data = new Outlook2007Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.outlook_data_container, data)
                .commit();

        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.outlook1) {
            Outlook2007Fragment outlook2007Fragment = new Outlook2007Fragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.outlook_data_container, outlook2007Fragment)
                    .commit();
        } else if (view.getId() == R.id.outlook2) {
            OutlookNewFragment relocateReprovisionFragment = new OutlookNewFragment();
            FragmentManager appBundles = getActivity().getSupportFragmentManager();
            appBundles.beginTransaction()
                    .replace(R.id.outlook_data_container, relocateReprovisionFragment)
                    .commit();

        } else if (view.getId() == R.id.outlook3) {
            OutlookFailing reprovisionFailFragment = new OutlookFailing();
            FragmentManager failReprov = getFragmentManager();
            failReprov.beginTransaction()
                    .replace(R.id.outlook_data_container, reprovisionFailFragment)
                    .commit();

        } /*else if (view.getId() == R.id.menuitem4) {
        MetalInstagramFragment insta = new MetalInstagramFragment();
        FragmentManager instaBundles = getFragmentManager();
        instaBundles.beginTransaction()
        .replace(R.id.data_container, insta)
        .commit();

        } else if (view.getId() == R.id.menuitem5) {
        MetalTwitterFragment twit = new MetalTwitterFragment();
        FragmentManager twitBundles = getFragmentManager();
        twitBundles.beginTransaction()
        .replace(R.id.data_container, twit)
        .commit();

        } else {
        NormalReprovisionFragment data = new NormalReprovisionFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
        .replace(R.id.data_container, data)
        .commit();
        }*/

    }

}
