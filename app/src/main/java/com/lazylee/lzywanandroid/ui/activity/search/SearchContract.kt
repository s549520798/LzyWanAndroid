package com.lazylee.lzywanandroid.ui.activity.search


import com.lazylee.lzywanandroid.ui.adapter.SearchHistoryAdapter
import com.lazylee.lzywanandroid.data.entity.Page
import com.lazylee.lzywanandroid.mvp.BasePresenter
import com.lazylee.lzywanandroid.mvp.BaseView

interface SearchContract {

    interface View : BaseView<Presenter> {
        /**
         * 当用户按下搜索按钮或者 软键盘中的enter进行搜索时，网络请求时显示progressBar
         */
        fun showProgressBar(show: Boolean)

        /**
         * 当editText为空时，不显示搜索按钮和清空按钮，否则显示
         *
         * @param show true表示显示搜索按钮，false表示不显示
         */
        fun showSearchAndCloseBtn(show: Boolean)

        /**
         * 是否显示提示界面（包含热搜词和搜搜历史）
         *
         * @param show true 表示显示；false表示隐藏
         */
        fun showOptionsView(show: Boolean)

        /**
         * 是否显示搜索结果界面
         *
         * @param show true 表示显示； false 表示隐藏
         */
        fun showResultView(show: Boolean)

        fun showEmptyResultView(show: Boolean)

        fun addChip(chip: String)

        fun addSearchResult(page: Page)
    }

    interface Presenter : BasePresenter {

        fun search(s: String)

        fun getHotKey()

        fun getSearchHistory(adapter: SearchHistoryAdapter?)
    }
}
