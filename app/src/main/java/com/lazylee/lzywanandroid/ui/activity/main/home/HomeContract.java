package com.lazylee.lzywanandroid.ui.activity.main.home;

import com.lazylee.lzywanandroid.ui.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

/**
 * home fragment contract
 * Created by lazylee on 2018/4/10.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void showStateView(boolean show);

        void showStateEmptyView(boolean show);

        void showUpLoadMore(boolean show);

        void showDownLoadMore(boolean show);

        void showProgressIndicator(boolean show);

        boolean isStateViewShow();

    }

    interface Presenter extends BasePresenter {

        void initArticles(ArticleAdapter adapter);

        void updateArticles(ArticleAdapter adapter);

        void loadMoreArticles(ArticleAdapter adapter);

    }
}
