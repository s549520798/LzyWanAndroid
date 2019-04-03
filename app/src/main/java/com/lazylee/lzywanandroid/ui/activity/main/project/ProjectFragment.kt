package com.lazylee.lzywanandroid.ui.activity.main.project

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar


import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.activity.main.project.fragment.ProjectVPFragment
import com.lazylee.lzywanandroid.ui.adapter.ProjectFragmentAdapter
import com.lazylee.lzywanandroid.data.entity.ProjectCategory
import com.lazylee.lzywanandroid.ui.view.LzyToast

import java.util.ArrayList


/**
 * project fragment in main activity
 * Created by lazylee on 2018/4/9.
 */

class ProjectFragment : Fragment(), ProjectContract.View {

    private var mPresenter: ProjectContract.Presenter? = null

    private var mProgressBar: ProgressBar? = null
    private var mTabLayout: TabLayout? = null
    private var mViewPager: ViewPager? = null

    private val mFragments = ArrayList<ProjectVPFragment>()
    private val mCategories = ArrayList<ProjectCategory>()
    internal var mFragmentAdapter: ProjectFragmentAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.project_fragment, container, false)
        mTabLayout = rootView.findViewById(R.id.tab_layout)
        mViewPager = rootView.findViewById(R.id.view_pager)
        mProgressBar = rootView.findViewById(R.id.progressBar)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = ProjectPresenter(this)
        val manager = fragmentManager
        mFragmentAdapter = ProjectFragmentAdapter(manager, mFragments, mCategories)
        mViewPager!!.adapter = mFragmentAdapter
        mTabLayout!!.setupWithViewPager(mViewPager)
    }

    override fun onStart() {
        super.onStart()
        if (mCategories.isEmpty()) {
            mPresenter!!.initProjectType(mFragments, mCategories)
        }
    }

    override fun setPresenter(presenter: ProjectContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showMessage(msg: String, type: Int) {
        LzyToast.showToast(msg, type)
    }

    override fun showMessage(msg: String) {
        LzyToast.showMessage(msg)
    }

    override fun showProgressBar(show: Boolean) {
        mProgressBar!!.visibility = if (show) View.VISIBLE else View.GONE
        mTabLayout!!.visibility = if (show) View.GONE else View.VISIBLE
        mViewPager!!.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun addTabs(categories: ArrayList<ProjectCategory>) {
        if (!categories.isEmpty()) {
            for (category in categories) {
                mTabLayout!!.addTab(mTabLayout!!.newTab().setText(category.name))
            }
            mFragmentAdapter!!.notifyDataSetChanged()
        }
    }

    companion object {

        val TAG = "ProjectFragment"

        fun newInstance(): ProjectFragment {
            return ProjectFragment()
        }
    }

}
