package com.lazylee.lzywanandroid.activity.main.home;

import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

/**
 * home fragment contract
 * Created by lazylee on 2018/4/10.
 */

public interface HomeContarct {

    interface View extends BaseView<Presenter>{

        void showProgressIndicator(Boolean show);

    }
    interface Presenter extends BasePresenter{

    }
}
