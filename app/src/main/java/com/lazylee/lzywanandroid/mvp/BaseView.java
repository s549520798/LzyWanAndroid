package com.lazylee.lzywanandroid.mvp;

/**
 * base view to impl
 * Created by lazylee on 2018/3/16.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    void showMessage(String msg,int type);

    void showMessage(String msg);


}
