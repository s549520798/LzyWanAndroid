package com.lazylee.lzywanandroid.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";


    @BindView(R.id.main_bottom_nav)
    BottomNavigationView mBottomNav;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.main_drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_left_nav)
    NavigationView mLeftNav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mBottomNav.setOnNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showProgressBar(boolean show) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:

                return true;
            case R.id.navigation_dashboard:

                return true;
            case R.id.navigation_notifications:

                return true;
        }
        return false;
    }
}
