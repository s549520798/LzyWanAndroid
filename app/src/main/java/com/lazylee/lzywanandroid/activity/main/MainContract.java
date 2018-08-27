package com.lazylee.lzywanandroid.activity.main;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

/**
 * main activity contract
 * Created by lazylee on 2018/4/3.
 */

public interface MainContract {

    int HOME = 0;
    int GUIDE = 1;
    int PROJECT = 2;

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void onFragmentChanged(FragmentManager manager, int type);

        void addAndHideFragment(FragmentManager manager, Fragment toAdd, Fragment toHide);

        void showAndHideFragment(FragmentManager manager, Fragment toShow, Fragment toHide);
    }
}
