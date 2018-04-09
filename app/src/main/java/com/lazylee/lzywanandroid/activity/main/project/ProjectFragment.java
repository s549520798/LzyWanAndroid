package com.lazylee.lzywanandroid.activity.main.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.lzywanandroid.R;

import butterknife.ButterKnife;

/**
 * project fragment in main activity
 * Created by lazylee on 2018/4/9.
 */

public class ProjectFragment extends Fragment {

    public static final String TAG = "ProjectFragment";

    public ProjectFragment() {

    }

    public static ProjectFragment newInstance(){
        return new ProjectFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
