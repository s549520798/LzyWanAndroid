package com.lazylee.lzywanandroid.ui.activity.main.project;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.ui.activity.main.project.fragment.ProjectVPFragment;
import com.lazylee.lzywanandroid.ui.adapter.ProjectFragmentAdapter;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;
import com.lazylee.lzywanandroid.ui.view.LzyToast;

import java.util.ArrayList;


/**
 * project fragment in main activity
 * Created by lazylee on 2018/4/9.
 */

public class ProjectFragment extends Fragment implements ProjectContract.View {

    public static final String TAG = "ProjectFragment";

    private ProjectContract.Presenter mPresenter;

    private ProgressBar mProgressBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ArrayList<ProjectVPFragment> mFragments = new ArrayList<>();
    private ArrayList<ProjectCategory> mCategories = new ArrayList<>();
    ProjectFragmentAdapter mFragmentAdapter;

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
        mProgressBar = rootView.findViewById(R.id.progressBar);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new ProjectPresenter(this);
        FragmentManager manager = getFragmentManager();
        mFragmentAdapter= new ProjectFragmentAdapter(manager, mFragments, mCategories);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mCategories.isEmpty()){
            mPresenter.initProjectType(mFragments,mCategories);
        }
    }

    @Override
    public void setPresenter(ProjectContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showMessage(String msg, int type) {
        LzyToast.showToast(msg, type);
    }

    @Override
    public void showMessage(String msg) {
        LzyToast.showMessage(msg);
    }

    @Override
    public void showProgressBar(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mTabLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        mViewPager.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void addTabs(ArrayList<ProjectCategory> categories) {
        if (!categories.isEmpty()) {
            for (ProjectCategory category : categories){
                mTabLayout.addTab(mTabLayout.newTab().setText(category.getName()));
            }
            mFragmentAdapter.notifyDataSetChanged();
        }
    }

}
