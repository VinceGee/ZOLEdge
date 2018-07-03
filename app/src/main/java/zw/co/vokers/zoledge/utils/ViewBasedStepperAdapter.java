package zw.co.vokers.zoledge.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by VinceGee on 1/19/2018.
 */

public abstract class ViewBasedStepperAdapter implements IStepperAdapter {

    private View mViews[];

    public ViewBasedStepperAdapter() {

    }

    public ViewBasedStepperAdapter(View[] views) {
        mViews = views;
    }

    public void setViews(View[] views) {
        mViews = views;
    }

    public View getView(int index) {
        return mViews[index];
    }

    @Override
    public int size() {
        return mViews.length;
    }

    @Override
    public View onCreateCustomView(int index, Context context, VerticalStepperItemView parent) {
        return mViews[index];
    }

}
