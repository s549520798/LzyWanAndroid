package com.lazylee.lzywanandroid.activity.main.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.data.entity.Article;
import com.lazylee.lzywanandroid.view.AppbarRefreshLayout;
import com.lazylee.lzywanandroid.view.LzyToast;
import com.lazylee.lzywanandroid.view.divider.ArticleRecycleDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeContarct.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "HomeFragment";

    private HomeContarct.Presenter mPresenter;
    private ArticleAdapter mAdapter;
    private ArrayList<Article> articles = new ArrayList<>();
    private int mPage = 0;
    private boolean mIsStateViewShow;

    @BindView(R.id.recycle_home_frag) RecyclerView mRecyclerView;
    @BindView(R.id.state_layout_root) ConstraintLayout mStateView;
    @BindView(R.id.state_image) ImageView mEmptyImage;
    @BindView(R.id.state_load_again) TextView mTvLoadAgain;
    @BindView(R.id.state_progress_bar) ProgressBar mStateProgressBar;
    @BindView(R.id.refresh_home_frag) AppbarRefreshLayout mRefreshLayout;
    @BindDrawable(R.drawable.ripple_text_bg) Drawable drawable;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        View mRootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomePresenter(this);
        mAdapter = new ArticleAdapter(articles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new ArticleRecycleDivider(getResources()
                .getColor(R.color.colorRecycleDivider)));
        mRefreshLayout.setOnRefreshListener(this);
        showStateView(true);
        showStateEmptyView(false);
        showProgressIndicator(true);
        mPresenter.updateArticles(mAdapter, mPage);
    }

    @Override
    public void onRefresh() {
        //TODO refresh the recyclerView here
        mPresenter.updateArticles(mAdapter, 0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(HomeContarct.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showMessage(String msg) {
        LzyToast.showMessage(msg, 1500);
    }

    @Override
    public void showStateView(boolean show) {
        mIsStateViewShow = show;
        mStateView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showStateEmptyView(boolean show) {
        if (isStateViewShow()) {
            mStateProgressBar.setVisibility(show ? View.GONE : View.VISIBLE);
            mEmptyImage.setVisibility(show ? View.VISIBLE : View.GONE);
            mTvLoadAgain.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showUpLoadMore(boolean show) {
        if (mRefreshLayout.isRefreshing() && show) {

        } else {
            mRefreshLayout.setRefreshing(show);
        }
    }

    @Override
    public void showDownLoadMore(boolean show) {

    }

    @Override
    public void showProgressIndicator(boolean show) {
        if (isStateViewShow()) {
            mEmptyImage.setVisibility(show ? View.GONE : View.VISIBLE);
            mTvLoadAgain.setVisibility(show ? View.GONE : View.VISIBLE);
            mStateProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isStateViewShow() {
        return mIsStateViewShow;
    }


}
