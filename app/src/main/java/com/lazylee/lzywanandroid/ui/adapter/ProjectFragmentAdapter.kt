package com.lazylee.lzywanandroid.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.lazylee.lzywanandroid.ui.activity.main.project.fragment.ProjectVPFragment
import com.lazylee.lzywanandroid.data.entity.ProjectCategory

import java.util.ArrayList


class ProjectFragmentAdapter : FragmentPagerAdapter {

    private val fragments: ArrayList<ProjectVPFragment>?
    private val categories: ArrayList<ProjectCategory>

    constructor(fm: FragmentManager) : super(fm) {}

    constructor(fm: FragmentManager, fragments: ArrayList<ProjectVPFragment>,
                categories: ArrayList<ProjectCategory>) : super(fm) {
        this.fragments = fragments
        this.categories = categories
    }

    override fun getItem(position: Int): Fragment {
        return fragments!![position]
    }

    override fun getCount(): Int {
        return if (fragments == null) 0 else categories.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categories[position].name
    }
}
