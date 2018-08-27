package com.lazylee.lzywanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lazylee.lzywanandroid.activity.main.project.fragment.ProjectVPFragment;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;

import java.util.ArrayList;


public class ProjectFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<ProjectVPFragment> fragments;
    private ArrayList<ProjectCategory> categories;

    public ProjectFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public ProjectFragmentAdapter(FragmentManager fm, ArrayList<ProjectVPFragment> fragments,
                                  ArrayList<ProjectCategory> categories) {
        super(fm);
        this.fragments = fragments;
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName();
    }
}
