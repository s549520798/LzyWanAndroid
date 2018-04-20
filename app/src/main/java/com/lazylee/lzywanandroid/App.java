package com.lazylee.lzywanandroid;

import android.app.Application;
import android.content.Context;

import com.lazylee.lzywanandroid.data.greendao.DaoMaster;
import com.lazylee.lzywanandroid.data.greendao.DaoSession;
import com.lazylee.lzywanandroid.view.LzyToast;

import org.greenrobot.greendao.database.Database;

import java.util.concurrent.TimeUnit;

/**
 * application
 * Created by lazylee on 2018/3/22.
 */

public class App extends Application {

    private static App mInstance;
    private Context mContext;
    private static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        if (mInstance == null) {
            mInstance = this;
        }
        LzyToast.initialize(mContext);
        //初始化daoSession
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "wan_android_db_encrypted" : "wan_android_db");
        Database database = ENCRYPTED ? helper.getEncryptedWritableDb("wan_android") : helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
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

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
