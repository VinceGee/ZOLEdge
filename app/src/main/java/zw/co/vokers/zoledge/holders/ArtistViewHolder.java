package zw.co.vokers.zoledge.holders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import zw.co.vokers.zoledge.R;

/**
 * Created by VinceGee on 3/21/2018.
 */

public class ArtistViewHolder extends ChildViewHolder {

    private TextView childTextView;

    public ArtistViewHolder(View itemView) {
        super(itemView);
        childTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
    }

    public void setArtistName(String name) {
        childTextView.setText(name);
    }
}
