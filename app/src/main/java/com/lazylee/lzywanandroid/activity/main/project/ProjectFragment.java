package com.lazylee.lzywanandroid.activity.main.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.activity.main.project.fragment.ProjectVPFragment;
import com.lazylee.lzywanandroid.adapter.ProjectFragmentAdapter;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;

import java.util.ArrayList;
import java.util.List;


/**
 * project fragment in main activity
 * Created by lazylee on 2018/4/9.
 */

public class ProjectFragment extends Fragment {

    public static final String TAG = "ProjectFragment";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ArrayList<ProjectVPFragment> mFragments = new ArrayList<>();
    private ArrayList<ProjectCategory> mCategory = new ArrayList<>();

    public ProjectFragment() {

    }

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.project_fragment, container, false);
        mTabLayout = rootView.findViewById(R.id.tab_layout);
        mViewPager = rootView.findViewById(R.id.view_pager);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager manager = getFragmentManager();
        ProjectFragmentAdapter fragmentAdapter = new ProjectFragmentAdapter(manager, mFragments, mCategory);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
