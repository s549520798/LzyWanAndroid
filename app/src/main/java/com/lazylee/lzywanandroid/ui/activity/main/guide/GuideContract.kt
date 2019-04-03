package com.lazylee.lzywanandroid.ui.activity.main.guide

import com.lazylee.lzywanandroid.mvp.BasePresenter
import com.lazylee.lzywanandroid.mvp.BaseView

class GuideContract {

    internal interface View : BaseView<Presenter>

    internal interface Presenter : BasePresenter
}
