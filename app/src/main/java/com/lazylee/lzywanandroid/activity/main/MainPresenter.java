package com.lazylee.lzywanandroid.activity.main;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static com.lazylee.lzywanandroid.tools.FragmentHelper.getCurrentVisibleFragment;
import static com.lazylee.lzywanandroid.tools.FragmentHelper.getFragmentByTag;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.activity.main.guide.GuideFragment;
import com.lazylee.lzywanandroid.activity.main.home.HomeFragment;
import com.lazylee.lzywanandroid.activity.main.project.ProjectFragment;


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

    @Override
    public void onFragmentChanged(FragmentManager manager, int type) {
        Fragment currentFragment = getCurrentVisibleFragment(manager);
        HomeFragment homeFragment = (HomeFragment) getFragmentByTag(manager, HomeFragment.TAG);
        GuideFragment guideFragment = (GuideFragment) getFragmentByTag(manager, GuideFragment.TAG);
        ProjectFragment projectFragment = (ProjectFragment) getFragmentByTag(manager, ProjectFragment.TAG);
        switch (type) {
            case MainContract.HOME:
                if (homeFragment == null){
                    addAndHideFragment(manager,HomeFragment.newInstance(),currentFragment);
                }else {
                    showAndHideFragment(manager,homeFragment,currentFragment);
                }
                break;
            case MainContract.GUIDE:
                if (guideFragment == null){
                    addAndHideFragment(manager,GuideFragment.newInstance(),currentFragment);
                }else {
                    showAndHideFragment(manager,guideFragment,currentFragment);
                }

                break;
            case MainContract.PROJECT:
                if (projectFragment == null){
                    addAndHideFragment(manager,ProjectFragment.newInstance(),currentFragment);
                }else {
                    showAndHideFragment(manager,projectFragment,currentFragment);
                }

                break;
        }

    }

    @Override
    public void addAndHideFragment(FragmentManager manager, Fragment toAdd, Fragment toHide) {
        toHide.onHiddenChanged(true);
        manager.beginTransaction()
                .hide(toHide)
                .add(R.id.fl_container, toAdd, toAdd.getClass().getSimpleName())
                .commit();
        toAdd.onHiddenChanged(false);
    }

    @Override
    public void showAndHideFragment(FragmentManager manager, Fragment toShow, Fragment toHide) {
        toHide.onHiddenChanged(true);
        manager.beginTransaction()
                .hide(toHide)
                .show(toShow)
                .commit();
        toShow.onHiddenChanged(false);
    }
}
