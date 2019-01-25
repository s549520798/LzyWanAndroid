package com.lazylee.lzywanandroid.activity.main;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.activity.login.LoginActivity;
import com.lazylee.lzywanandroid.activity.main.home.HomeFragment;
import com.lazylee.lzywanandroid.activity.search.SearchActivity;
import com.lazylee.lzywanandroid.view.LzyToast;


public class MainActivity extends AppCompatActivity implements MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private MainContract.Presenter mPresenter;
    private CoordinatorLayout mCoordinatorLayout;
    private BottomNavigationView mBottomNav;
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mLeftNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            setContentView(R.layout.main_activity);
            if (!isLogin()) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            initViews();
            mPresenter = new MainPresenter(this);
            setSupportActionBar(mToolBar);
            mBottomNav.setOnNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

            mToolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            mDrawerLayout.addDrawerListener(toggle);
            mLeftNav.setNavigationItemSelectedListener(itemSelectedListener);
            toggle.syncState();
            setEnterFragment();
        }

    }


    private boolean isLogin() {
        //TODO 从数据库中判断是否用户已经登陆，这里模拟没有登陆的状态
        return false;
    }

    private void setEnterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_toolbar_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_toolbar_history:
                //TODO 跳转到 history 页面
                return true;
            case R.id.menu_toolbar_search:
                //TODO 跳转到 search 页面
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showMessage(String msg, int type) {
        LzyToast.showToast(msg, type);
    }

    @Override
    public void showMessage(String msg) {
        LzyToast.showMessage(msg, 1500);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mPresenter.onFragmentChanged(getSupportFragmentManager(), MainContract.HOME);
                return true;
            case R.id.navigation_guide:
                mPresenter.onFragmentChanged(getSupportFragmentManager(), MainContract.GUIDE);
                return true;
            case R.id.navigation_project:
                mPresenter.onFragmentChanged(getSupportFragmentManager(), MainContract.PROJECT);
                return true;
        }
        return false;
    }

    NavigationView.OnNavigationItemSelectedListener itemSelectedListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.drawer_nav_menu_tree:
                        showMessage("知识体系");
                        return true;
                    case R.id.drawer_nav_menu_tools:
                        LzyToast.showError("常用工具", 1500);
                        return true;
                    case R.id.drawer_nav_menu_about:
                        LzyToast.showAlert("关于", 1500);
                        return true;
                    case R.id.drawer_nav_menu_report_issue:
                        showMessage("提交bug");
                        return true;
                }
                return false;
            };

    private void initViews() {
        mCoordinatorLayout = findViewById(R.id.main_cl);
        mBottomNav = findViewById(R.id.main_bottom_nav);
        mToolBar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.main_drawer);
        mLeftNav = findViewById(R.id.main_left_nav);

        Log.d(TAG, "initViews: bottom nav dependency view :"
                + mCoordinatorLayout.getDependencies(mBottomNav).size());
    }
}
