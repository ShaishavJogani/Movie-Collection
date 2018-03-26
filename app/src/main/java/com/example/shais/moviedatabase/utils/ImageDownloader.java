package com.example.shais.moviedatabase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shaishav on 3/25/2018.
 * Credit: https://github.com/vad-zuev/ImageDownloader
 */

public class ImageDownloader {

    private static ImageDownloader that;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;
    private static final Map<Class, String> mErrorMessageMap = new HashMap<>(7);

    private ImageDownloader(Context context) {
        mContext = context.getApplicationContext();
        mRequestQueue = getRequestQ();
        final int MAX_CACHE_SIZE = 30;
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<>(MAX_CACHE_SIZE);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
        //initialize error map
        mErrorMessageMap.put(NoConnectionError.class, "No connection");
        mErrorMessageMap.put(AuthFailureError.class, "Authentication failed");
        mErrorMessageMap.put(TimeoutError.class, "Connection timeout");
        mErrorMessageMap.put(ParseError.class, "Failed to parse the response");
        mErrorMessageMap.put(ServerError.class, "Internal server error");
        mErrorMessageMap.put(NetworkError.class, "Network error");
    }

    /**
     * @param error the occurred VolleyError
     * @return the message based on the type of the error
     */
    public String checkError(VolleyError error) {
        return mErrorMessageMap.get(error.getClass()) == null ? "Unknown error" :
                mErrorMessageMap.get(error.getClass());
    }

    public static synchronized ImageDownloader getInstance(Context context) {
        if (that == null)
            that = new ImageDownloader(context);
        return that;
    }


    public RequestQueue getRequestQ() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mContext);
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}