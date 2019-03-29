package com.lazylee.lzywanandroid.ui.base;

import android.os.Bundle;
import android.util.Log;

import com.lazylee.lzywanandroid.BuildConfig;
import com.lazylee.lzywanandroid.ui.view.LzyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private boolean needTrace = BuildConfig.DEBUG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needTrace) {
            //TODO trace
            Log.i(TAG, "onCreate: ");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (needTrace) {
            Log.i(TAG, "onStart: ");
        }
    }

    @Override
    protected void onStop() {
        if (needTrace) {
            Log.i(TAG, "onStop: ");
        }

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (needTrace) {
            Log.i(TAG, "onDestroy: ");
        }

        super.onDestroy();
    }

    private boolean isLogin() {
        //TODO 判断是否登录状态
        return false;
    }

    private void showMessage(String msg, int type) {
        LzyToast.showToast(msg, type);
    }
}
