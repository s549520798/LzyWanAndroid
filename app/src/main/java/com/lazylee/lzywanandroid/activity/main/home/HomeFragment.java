package com.lazylee.lzywanandroid.activity.main.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.adapter.viewholder.ArticleViewHolder;
import com.lazylee.lzywanandroid.entity.Article;
import com.lazylee.lzywanandroid.view.LzyToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeContarct.View{

    public static final String TAG = "HomeFragment";

    private HomeContarct.Presenter mPresenter;
    private ArticleAdapter mAdapter;
    private ArrayList<Article> articles = new ArrayList<>();

    @BindView(R.id.recycle_home_frag)
    RecyclerView mRecyclerView;
    @BindView(R.id.state_layout_root)
    NestedScrollView mStateView;
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
        mPresenter = new HomePresenter(this);
        mAdapter = new ArticleAdapter(articles);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.loadArticles(mAdapter);
        return mRootView;
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
        LzyToast.showMessage(msg,1500);
    }

    @Override
    public void showStateView(boolean show) {
        mStateView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgressIndicator(Boolean show) {

    }
}
