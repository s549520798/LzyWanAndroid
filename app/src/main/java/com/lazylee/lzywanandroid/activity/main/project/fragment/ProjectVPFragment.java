package com.lazylee.lzywanandroid.activity.main.project.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.lazylee.lzywanandroid.R;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectVPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectVPFragment extends Fragment {



    public ProjectVPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProjectVPFragment.
     */

    public static ProjectVPFragment newInstance() {
        ProjectVPFragment fragment = new ProjectVPFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.project_viewpager_fragment, container, false);
    }

}
