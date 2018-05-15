package com.lazylee.lzywanandroid.activity.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
        void onFragmentChanged(androidx.fragment.app.FragmentManager manager, int type);

        void addAndHideFragment(androidx.fragment.app.FragmentManager manager, androidx.fragment.app.Fragment toAdd, Fragment toHide);

        void showAndHideFragment(androidx.fragment.app.FragmentManager manager, androidx.fragment.app.Fragment toShow, Fragment toHide);
    }
}
