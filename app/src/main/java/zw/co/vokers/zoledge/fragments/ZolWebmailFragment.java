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
import zw.co.vokers.zoledge.utils.ExpandableTextView;

public class ZolWebmailFragment extends Fragment {

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

        rootView = LayoutInflater.from(context).inflate(R.layout.zol_webmail, container, false);
        //callBtn = (Button) rootView.findViewById(R.id.button);

        TextView cash = rootView.findViewById(R.id.zol_webmail_txt);

        cash.setText(Html.fromHtml(getString(R.string.zol_webmail_text)));

        ((TextView) rootView.findViewById(R.id.webmail1).findViewById(R.id.eptitle1)).setText("Forwarding Emails");
        ((TextView) rootView.findViewById(R.id.webmail2).findViewById(R.id.eptitle2)).setText("Blacklisting/Whitelisting Email Addresses or Domains");
        ((TextView) rootView.findViewById(R.id.webmail3).findViewById(R.id.eptitle3)).setText("Changing the Name That Appears On Sent Mail in Webmail");
        ((TextView) rootView.findViewById(R.id.webmail4).findViewById(R.id.eptitle4)).setText("Prevent Webmail Disk Space from Running Out");

        ExpandableTextView expTv1 = (ExpandableTextView) rootView.findViewById(R.id.webmail1).findViewById(R.id.expand_text_view1);
        ExpandableTextView expTv2 = (ExpandableTextView) rootView.findViewById(R.id.webmail2).findViewById(R.id.expand_text_view2);
        ExpandableTextView expTv3 = (ExpandableTextView) rootView.findViewById(R.id.webmail3).findViewById(R.id.expand_text_view3);
        ExpandableTextView expTv4 = (ExpandableTextView) rootView.findViewById(R.id.webmail4).findViewById(R.id.expand_text_view4);

        expTv1.setText(getString(R.string.text1));
        expTv2.setText(getString(R.string.text2));
        expTv3.setText(getString(R.string.text3));
        expTv4.setText(getString(R.string.text4));

        return rootView;
    }

}

