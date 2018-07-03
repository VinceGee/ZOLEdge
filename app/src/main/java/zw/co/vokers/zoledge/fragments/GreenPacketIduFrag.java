package zw.co.vokers.zoledge.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import zw.co.vokers.zoledge.R;

public class GreenPacketIduFrag extends Fragment {

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

        rootView = LayoutInflater.from(context).inflate(R.layout.gpidu_wimax, container, false);
        //callBtn = (Button) rootView.findViewById(R.id.button);

        TextView cash = rootView.findViewById(R.id.gpidu_txt);

        cash.setText(Html.fromHtml(getString(R.string.gpidu_text)));

        return rootView;
    }

}