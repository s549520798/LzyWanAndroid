package com.lazylee.lzywanandroid.activity.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.activity.main.home.HomeFragment;
import com.lazylee.lzywanandroid.activity.search.SearchActivity;
import com.lazylee.lzywanandroid.view.LzyToast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    private MainContract.Presenter mPresenter;

    @BindView(R.id.main_bottom_nav) BottomNavigationView mBottomNav;
    @BindView(R.id.toolbar) Toolbar mToolBar;
    @BindView(R.id.main_drawer) DrawerLayout mDrawerLayout;
    @BindView(R.id.main_left_nav) NavigationView mLeftNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
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
            new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.drawer_nav_menu_tree:
                    showMessage("知识体系");
                    return true;
                case R.id.drawer_nav_menu_tools:
                    showMessage("常用工具");
                    return true;
                case R.id.drawer_nav_menu_about:
                    showMessage("关于");
                    return true;
                case R.id.drawer_nav_menu_report_issue:
                    showMessage("提交bug");
                    return true;
            }
            return false;
        }
    };

}
