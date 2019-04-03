package com.lazylee.lzywanandroid.ui.activity.main.project

import com.lazylee.lzywanandroid.ui.activity.main.project.fragment.ProjectVPFragment
import com.lazylee.lzywanandroid.data.entity.ProjectCategory
import com.lazylee.lzywanandroid.mvp.BasePresenter
import com.lazylee.lzywanandroid.mvp.BaseView

import java.util.ArrayList

/**
 * project contract
 */
interface ProjectContract {

    interface View : BaseView<Presenter> {

        fun showProgressBar(show: Boolean)

        fun addTabs(categories: ArrayList<ProjectCategory>)
    }

    interface Presenter : BasePresenter {
        fun initProjectType(fragments: ArrayList<ProjectVPFragment>, categories: ArrayList<ProjectCategory>)
    }
}
