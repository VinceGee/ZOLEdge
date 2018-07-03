package zw.co.vokers.zoledge;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import zw.co.vokers.zoledge.utils.FontsOverride;
import zw.co.vokers.zoledge.utils.LruBitmapCache;
import zw.co.vokers.zoledge.utils.TypefaceUtil;

/**
 * Created by VinceGee on 1/14/2018.
 */

public class ZOLEdgeApp extends Application {
    public static final String TAG = ZOLEdgeApp.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static ZOLEdgeApp mInstance;

    public static volatile Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/ubuntu.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        mInstance = this;
        mApplicationContext=getApplicationContext();
        //super.onCreate();
        //FontsOverride.setDefaultFont(this, "normal", "fonts/ubuntu.ttf");
        //FontsOverride.setDefaultFont(this, "MONOSPACE", "MyFontAsset2.ttf");
        //FontsOverride.setDefaultFont(this, "SERIF", "MyFontAsset3.ttf");
        //FontsOverride.setDefaultFont(this, "SANS_SERIF", "MyFontAsset4.ttf");
    }

    public static synchronized ZOLEdgeApp getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }
}
