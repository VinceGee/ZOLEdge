package zw.co.vokers.zoledge.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by VinceGee on 3/21/2018.
 */

public class Banks extends ExpandableGroup<Artist> {

    private int iconResId;

    public Banks(String title, List<Artist> items, int iconResId) {
        super(title, items);
        this.iconResId = iconResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banks)) return false;

        Banks banks = (Banks) o;

        return getIconResId() == banks.getIconResId();

    }

    @Override
    public int hashCode() {
        return getIconResId();
    }
}

