package com.lazylee.lzywanandroid.activity.main;

/**
 * main activity presenter
 * Created by lazylee on 2018/4/3.
 */

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }
}
