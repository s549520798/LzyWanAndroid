package com.lazylee.lzywanandroid.activity.search;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.lazylee.lzywanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    @BindView(R.id.searchView)
    RelativeLayout mSearchView;
    @BindView(R.id.backBtn)
    ImageButton mBackBtn;
    @BindView(R.id.closeBtn)
    ImageButton mCloseBtn;
    @BindView(R.id.searchBtn)
    ImageButton mSearchBtn;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recycler_result)
    RecyclerView mResultView;
    @BindView(R.id.search_options)
    LinearLayout mOptionsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
    }
}
