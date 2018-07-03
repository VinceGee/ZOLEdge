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

public class ZolHostedDomainEmailFrag extends Fragment {

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

        rootView = LayoutInflater.from(context).inflate(R.layout.zol_hosted_domain_email, container, false);
        //callBtn = (Button) rootView.findViewById(R.id.button);

        TextView cash = rootView.findViewById(R.id.zol_hosteddomain_txt);

        cash.setText(Html.fromHtml(getString(R.string.zol_hosteddomain_text)));

        return rootView;
    }

}
