package com.lazylee.lzywanandroid.activity.main.project;

import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

/**
 * project contract
 */
public interface ProjectContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{
        void initProjectType();
    }
}
