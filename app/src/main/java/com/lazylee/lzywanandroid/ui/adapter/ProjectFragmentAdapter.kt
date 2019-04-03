package com.lazylee.lzywanandroid.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.lazylee.lzywanandroid.ui.activity.main.project.fragment.ProjectVPFragment
import com.lazylee.lzywanandroid.data.entity.ProjectCategory

import java.util.ArrayList


class ProjectFragmentAdapter : FragmentPagerAdapter {

    private var mFragments: ArrayList<ProjectVPFragment>? = null
    private var mCategories: ArrayList<ProjectCategory>? = null

    constructor(fm: FragmentManager) : super(fm)

    constructor(fm: FragmentManager?, fragments: ArrayList<ProjectVPFragment>,
                categories: ArrayList<ProjectCategory>) : super(fm!!) {
        this.mFragments = fragments
        this.mCategories = categories
    }

    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

    override fun getCount(): Int {
        return if (mFragments == null) 0 else mCategories!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mCategories!![position].name
    }
}
