package com.lazylee.lzywanandroid.ui.activity.main


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.lazylee.lzywanandroid.tools.FragmentHelper.getCurrentVisibleFragment
import com.lazylee.lzywanandroid.tools.FragmentHelper.getFragmentByTag

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.activity.main.guide.GuideFragment
import com.lazylee.lzywanandroid.ui.activity.main.home.HomeFragment
import com.lazylee.lzywanandroid.ui.activity.main.project.ProjectFragment


/**
 * main activity presenter
 * Created by lazylee on 2018/4/3.
 */

class MainPresenter(private val mView: MainContract.View) : MainContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    override fun onFragmentChanged(manager: FragmentManager, type: Int) {
        val currentFragment = getCurrentVisibleFragment(manager)
        val homeFragment = getFragmentByTag(manager, HomeFragment.TAG) as HomeFragment?
        val guideFragment = getFragmentByTag(manager, GuideFragment.TAG) as GuideFragment?
        val projectFragment = getFragmentByTag(manager, ProjectFragment.TAG) as ProjectFragment?
        when (type) {
            MainContract.HOME -> if (homeFragment == null) {
                addAndHideFragment(manager, HomeFragment.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, homeFragment, currentFragment)
            }
            MainContract.GUIDE -> if (guideFragment == null) {
                addAndHideFragment(manager, GuideFragment.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, guideFragment, currentFragment)
            }
            MainContract.PROJECT -> if (projectFragment == null) {
                addAndHideFragment(manager, ProjectFragment.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, projectFragment, currentFragment)
            }
        }

    }

    override fun addAndHideFragment(manager: FragmentManager, toAdd: Fragment, toHide: Fragment?) {
        toHide!!.onHiddenChanged(true)
        manager.beginTransaction()
                .hide(toHide)
                .add(R.id.fl_container, toAdd, toAdd.javaClass.getSimpleName())
                .commit()
        toAdd.onHiddenChanged(false)
    }

    override fun showAndHideFragment(manager: FragmentManager, toShow: Fragment, toHide: Fragment?) {
        toHide!!.onHiddenChanged(true)
        manager.beginTransaction()
                .hide(toHide)
                .show(toShow)
                .commit()
        toShow.onHiddenChanged(false)
    }

    companion object {
        private val TAG = "MainPresenter"
    }
}
