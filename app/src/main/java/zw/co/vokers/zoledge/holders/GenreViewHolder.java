package zw.co.vokers.zoledge.holders;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.models.Banks;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by VinceGee on 3/21/2018.
 */

public class GenreViewHolder extends GroupViewHolder {

    //private TextView genreName;
    private ImageView arrow;
    private ImageView icon;

    public GenreViewHolder(View itemView) {
        super(itemView);
        //genreName = (TextView) itemView.findViewById(R.id.list_item_genre_name);
        arrow = (ImageView) itemView.findViewById(R.id.list_item_genre_arrow);
        icon = (ImageView) itemView.findViewById(R.id.list_item_genre_icon);
    }

    public void setGenreTitle(ExpandableGroup genre) {
        if (genre instanceof Banks) {
            //genreName.setText(genre.getTitle());
            icon.setBackgroundResource(((Banks) genre).getIconResId());
        }
        /*if (genre instanceof MultiCheckGenre) {
            genreName.setText(genre.getTitle());
            icon.setBackgroundResource(((MultiCheckGenre) genre).getIconResId());
        }
        if (genre instanceof SingleCheckGenre) {
            genreName.setText(genre.getTitle());
            icon.setBackgroundResource(((SingleCheckGenre) genre).getIconResId());
        }*/
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
