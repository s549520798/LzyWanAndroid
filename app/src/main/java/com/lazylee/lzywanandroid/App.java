package com.lazylee.lzywanandroid;

import android.app.Application;
import android.content.Context;

import com.lazylee.lzywanandroid.view.LzyToast;

import java.util.concurrent.TimeUnit;

/**
 *
 * Created by lazylee on 2018/3/22.
 */

public class App extends Application {

    private static App mInstance;
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        if (mInstance == null) {
            mInstance = this;
        }
        LzyToast.initialize(mContext);
    }

    public static App getInstance() {
        return mInstance;
    }

    public Context getContext() {
        if (mContext == null) {
            mContext = getApplicationContext();
        }
        return mContext;
    }
}
