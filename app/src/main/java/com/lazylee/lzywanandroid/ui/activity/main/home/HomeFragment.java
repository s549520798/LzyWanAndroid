package com.lazylee.lzywanandroid.ui.activity.main.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.ui.activity.web.WebActivity;
import com.lazylee.lzywanandroid.ui.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.data.entity.Article;
import com.lazylee.lzywanandroid.ui.view.AppbarRefreshLayout;
import com.lazylee.lzywanandroid.ui.view.LzyToast;
import com.lazylee.lzywanandroid.ui.view.divider.ArticleRecycleDivider;

import java.util.ArrayList;


/**
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    public static final String TAG = "HomeFragment";

    private HomeContract.Presenter mPresenter;
    private ArticleAdapter mAdapter;
    private ArrayList<Article> articles = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private ConstraintLayout mStateView;
    private ImageView mEmptyImage;
    private TextView mTvLoadAgain;
    private ProgressBar mStateProgressBar;
    private AppbarRefreshLayout mRefreshLayout;


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
        mRecyclerView = mRootView.findViewById(R.id.recycle_home_frag);
        mStateView = mRootView.findViewById(R.id.state_layout_root);
        mEmptyImage = mRootView.findViewById(R.id.state_image);
        mTvLoadAgain = mRootView.findViewById(R.id.state_load_again);
        mStateProgressBar = mRootView.findViewById(R.id.state_progress_bar);
        mRefreshLayout = mRootView.findViewById(R.id.refresh_home_frag);

        mTvLoadAgain.setOnClickListener(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomePresenter(this);
        mAdapter = new ArticleAdapter(articles);
        mAdapter.setItemClickListener((view1, position) -> {
                Intent intent = new Intent(view1.getContext(), WebActivity.class);
                intent.putExtra("link", articles.get(position).getLink());
                startActivity(intent);
        });
        //mAdapter.setItemLongClickListener((view12, position) -> false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new ArticleRecycleDivider(getResources()
                .getColor(R.color.colorRecycleDivider)));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING &&
                        !recyclerView.canScrollVertically(1)){
                   //TODO 加载更过操作
                    mPresenter.loadMoreArticles(mAdapter);
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(this);
        showStateView(true);
        showStateEmptyView(false);
        showProgressIndicator(true);
        mPresenter.initArticles(mAdapter);
    }

    @Override
    public void onRefresh() {

        mPresenter.updateArticles(mAdapter);
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
    public void setPresenter(HomeContract.Presenter presenter) {
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
    public void showStateView(boolean show) {
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
        return mStateView.getVisibility() == View.VISIBLE;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.state_load_again:
                if (isStateViewShow()){
                    mPresenter.initArticles(mAdapter);
                }
                break;
            default:
                break;
        }
    }
}
