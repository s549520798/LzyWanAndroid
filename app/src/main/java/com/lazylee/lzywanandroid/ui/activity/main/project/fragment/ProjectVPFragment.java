package com.lazylee.lzywanandroid.ui.activity.main.project.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;


public class ProjectVPFragment extends Fragment {

    private static final String TAG = "ProjectVPFragment";

    private static final String CATEGORY = "category";
    private ProjectCategory mCategory;

    private TextView mTextView;


    public ProjectVPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProjectVPFragment.
     */

    public static ProjectVPFragment newInstance(ProjectCategory category) {
        ProjectVPFragment fragment = new ProjectVPFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CATEGORY,category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mCategory = bundle.getParcelable(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.project_viewpager_fragment,container,false);
        mTextView = rootView.findViewById(R.id.textView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mCategory != null){
            //Log.d(TAG, "onViewCreated: category" + mCategory.toString());
            mTextView.setText(mCategory.getName());
        }
    }
}
