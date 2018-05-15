package com.lazylee.lzywanandroid.activity.search;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

public interface SearchContract {

    interface View extends BaseView<Presenter> {
        /**
         * 当用户按下搜索按钮或者 软键盘中的enter进行搜索时，网络请求时显示progressBar
         */
        void showProgressBar(boolean show);

        /**
         * 当editText为空时，不显示搜索按钮和清空按钮，否则显示
         *
         * @param show true表示显示搜索按钮，false表示不显示
         */
        void showSearchAndCloseBtn(boolean show);

        /**
         * 是否显示提示界面（包含热搜词和搜搜历史）
         *
         * @param show true 表示显示；false表示隐藏
         */
        void showOptionsView(boolean show);

        /**
         * 是否显示搜索结果界面
         *
         * @param show true 表示显示； false 表示隐藏
         */
        void showResultView(boolean show);

        void addChip(String chip);
    }

    interface Presenter extends BasePresenter {

        void search(String s);

        void getHotKey();

        void getSearchHistory();
    }
}
