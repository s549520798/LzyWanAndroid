package com.lazylee.lzywanandroid.ui.activity.enter;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.ui.activity.main.MainActivity;

import java.util.concurrent.TimeUnit;

public class EnterActivity extends AppCompatActivity {

    private static final String TAG = "EnterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.enter_activity);
        Observable.interval(1L, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    Log.i(TAG, "accept: " + aLong);
                    if (aLong == 5L) {
                        startActivity(new Intent(EnterActivity.this, MainActivity.class));
                    }
                }).dispose();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //startActivity(new Intent(this, MainActivity.class));
    }
}
