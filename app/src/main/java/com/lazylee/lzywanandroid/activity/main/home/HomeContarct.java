package com.lazylee.lzywanandroid.activity.main.home;

import com.lazylee.lzywanandroid.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

/**
 * home fragment contract
 * Created by lazylee on 2018/4/10.
 */

public interface HomeContarct {

    interface View extends BaseView<Presenter> {

        void showStateView(boolean show);

        void showStateEmptyView(boolean show);

        void showUpLoadMore(boolean show);

        void showDownLoadMore(boolean show);

        void showProgressIndicator(Boolean show);

    }

    interface Presenter extends BasePresenter {
        void loadArticles(ArticleAdapter adapter);


    }
}
