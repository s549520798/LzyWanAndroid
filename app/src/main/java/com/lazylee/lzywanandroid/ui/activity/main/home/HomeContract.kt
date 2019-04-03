package com.lazylee.lzywanandroid.ui.activity.main.home

import com.lazylee.lzywanandroid.ui.adapter.ArticleAdapter
import com.lazylee.lzywanandroid.mvp.BasePresenter
import com.lazylee.lzywanandroid.mvp.BaseView

/**
 * home fragment contract
 * Created by lazylee on 2018/4/10.
 */

interface HomeContract {

    interface View : BaseView<Presenter> {

        val isStateViewShow: Boolean

        fun showStateView(show: Boolean)

        fun showStateEmptyView(show: Boolean)

        fun showUpLoadMore(show: Boolean)

        fun showDownLoadMore(show: Boolean)

        fun showProgressIndicator(show: Boolean)

    }

    interface Presenter : BasePresenter {

        fun initArticles(adapter: ArticleAdapter?)

        fun updateArticles(adapter: ArticleAdapter?)

        fun loadMoreArticles(adapter: ArticleAdapter?)

    }
}
