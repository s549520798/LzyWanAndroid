package com.lazylee.lzywanandroid.ui.activity.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem


import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.activity.login.LoginActivity
import com.lazylee.lzywanandroid.ui.activity.main.home.HomeFragment
import com.lazylee.lzywanandroid.ui.activity.search.SearchActivity
import com.lazylee.lzywanandroid.ui.view.LzyToast


class MainActivity : AppCompatActivity(), MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {
    private var mPresenter: MainContract.Presenter? = null
    private var mCoordinatorLayout: CoordinatorLayout? = null
    private var mBottomNav: BottomNavigationView? = null
    private var mToolBar: Toolbar? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mLeftNav: NavigationView? = null


    //TODO 从数据库中判断是否用户已经登陆，这里模拟没有登陆的状态
    private val isLogin: Boolean
        get() = false

    private var itemSelectedListener = { item: MenuItem ->
        when (item.itemId) {
            R.id.drawer_nav_menu_tree -> {
                showMessage("知识体系")
                true
            }
            R.id.drawer_nav_menu_tools -> {
                LzyToast.showError("常用工具", 1500)
                true
            }
            R.id.drawer_nav_menu_about -> {
                LzyToast.showAlert("关于", 1500)
                true
            }
            R.id.drawer_nav_menu_report_issue -> {
                showMessage("提交bug")
                true
            }
            else ->
                false

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setContentView(R.layout.main_activity)
            if (!isLogin) {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
            initViews()
            mPresenter = MainPresenter(this)
            setSupportActionBar(mToolBar)
            mBottomNav!!.setOnNavigationItemSelectedListener(this)
            val toggle = ActionBarDrawerToggle(
                    this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

            mToolBar!!.setTitleTextColor(resources.getColor(R.color.colorWhite))
            mDrawerLayout!!.addDrawerListener(toggle)
            mLeftNav!!.setNavigationItemSelectedListener(itemSelectedListener)
            toggle.syncState()
            setEnterFragment()
        }

    }

    private fun setEnterFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_container, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit()
    }

    override fun onBackPressed() {
        if (mDrawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        val menuItem = menu.findItem(R.id.menu_toolbar_search)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_toolbar_history ->
                //TODO 跳转到 history 页面
                true
            R.id.menu_toolbar_search -> {
                //TODO 跳转到 search 页面
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    override fun setPresenter(presenter: MainContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showMessage(msg: String, type: Int) {
        LzyToast.showToast(msg, type)
    }

    override fun showMessage(msg: String) {
        LzyToast.showMessage(msg, 1500)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                mPresenter!!.onFragmentChanged(supportFragmentManager, MainContract.HOME)
                return true
            }
            R.id.navigation_guide -> {
                mPresenter!!.onFragmentChanged(supportFragmentManager, MainContract.GUIDE)
                return true
            }
            R.id.navigation_project -> {
                mPresenter!!.onFragmentChanged(supportFragmentManager, MainContract.PROJECT)
                return true
            }
        }
        return false
    }

    private fun initViews() {
        mCoordinatorLayout = findViewById(R.id.main_cl)
        mBottomNav = findViewById(R.id.main_bottom_nav)
        mToolBar = findViewById(R.id.toolbar)
        mDrawerLayout = findViewById(R.id.main_drawer)
        mLeftNav = findViewById(R.id.main_left_nav)

        Log.d(TAG, "initViews: bottom nav dependency view :" + mCoordinatorLayout!!.getDependencies(mBottomNav!!).size)
    }

    companion object {

        private val TAG = "MainActivity"
    }
}
