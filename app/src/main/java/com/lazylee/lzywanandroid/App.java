package com.lazylee.lzywanandroid;

import android.app.Application;
import android.content.Context;

import com.lazylee.lzywanandroid.ui.view.LzyToast;


/**
 * application
 * Created by lazylee on 2018/3/22.
 */

public class App extends Application {

    private static volatile App mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mContext == null) {
            mContext = this;
        }
        LzyToast.initialize(mContext.getContext());
    }

    public static App getInstance() {
        return mContext;
    }

    public Context getContext() {
        return getApplicationContext();
    }


}
