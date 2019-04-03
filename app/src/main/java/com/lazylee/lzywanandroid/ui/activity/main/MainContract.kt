package com.lazylee.lzywanandroid.ui.activity.main


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.lazylee.lzywanandroid.mvp.BasePresenter
import com.lazylee.lzywanandroid.mvp.BaseView

/**
 * main activity contract
 * Created by lazylee on 2018/4/3.
 */

interface MainContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {
        fun onFragmentChanged(manager: FragmentManager, type: Int)

        fun addAndHideFragment(manager: FragmentManager, toAdd: Fragment, toHide: Fragment?)

        fun showAndHideFragment(manager: FragmentManager, toShow: Fragment, toHide: Fragment?)
    }

    companion object {

        val HOME = 0
        val GUIDE = 1
        val PROJECT = 2
    }
}
