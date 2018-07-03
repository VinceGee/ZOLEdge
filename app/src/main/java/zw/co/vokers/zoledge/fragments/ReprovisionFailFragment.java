package zw.co.vokers.zoledge.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.utils.VerticalStepperItemView;

public class ReprovisionFailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reprov_fail, parent, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView cash = view.findViewById(R.id.reason_prov);

        cash.setText(Html.fromHtml(getString(R.string.reasons_reprov)));

    }


}

