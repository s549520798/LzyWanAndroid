package com.lazylee.lzywanandroid.ui.activity.register

import com.lazylee.lzywanandroid.mvp.BasePresenter
import com.lazylee.lzywanandroid.mvp.BaseView

/**
 * register contract
 * Created by lazylee on 2018/3/22.
 */

interface RegisterContract {

    interface View : BaseView<Presenter> {

        fun enableRegisterButton(enable: Boolean)

        fun showUserNameError(error: String)

        fun showPasswordError(error: String)

        fun showRepasswordError(error: String)

        fun showProgressBar(show: Boolean)
    }

    interface Presenter : BasePresenter {

        fun register(username: String, password: String, repassword: String)
    }
}
