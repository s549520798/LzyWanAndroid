package com.lazylee.lzywanandroid.adapter;

import com.lazylee.lzywanandroid.activity.main.project.fragment.ProjectVPFragment;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
        return categories == null ? 0 : categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName();
    }
}
