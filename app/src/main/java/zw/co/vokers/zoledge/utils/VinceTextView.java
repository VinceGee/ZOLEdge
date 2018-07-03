package zw.co.vokers.zoledge.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by VinceGee on 07/29/2016.
 */
public class VinceTextView extends TextView {

    public VinceTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public VinceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VinceTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ubuntu.ttf");
            setTypeface(tf);
        }
    }

}
