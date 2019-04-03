package com.lazylee.lzywanandroid.mvp

/**
 * base view to impl
 * Created by lazylee on 2018/3/16.
 */

interface BaseView<T : BasePresenter> {

    fun setPresenter(presenter: T)

    fun showMessage(msg: String, type: Int)

    fun showMessage(msg: String)


}
