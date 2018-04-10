package com.lazylee.lzywanandroid.activity.main.home;

/**
 * home fragment presenter
 * Created by lazylee on 2018/4/10.
 */

public class HomePresenter implements HomeContarct.Presenter {
    private HomeContarct.View mView;

    public HomePresenter(HomeContarct.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }
}
