package com.lazylee.lzywanandroid.ui.activity.login

import com.lazylee.lzywanandroid.mvp.BasePresenter
import com.lazylee.lzywanandroid.mvp.BaseView

/**
 * 登录分离
 * Created by lazylee on 2018/3/16.
 */

interface LoginContract {

    interface View : BaseView<Presenter> {

        fun showUsernameError(error: String)

        fun showPasswordError(error: String)

        fun enableLoginButton(enable: Boolean)

        fun showProgressBar(show: Boolean)

    }

    interface Presenter : BasePresenter {

        fun login(userName: String, password: String)

    }
}
