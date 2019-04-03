package com.lazylee.lzywanandroid.ui.activity.login

import android.text.TextUtils
import android.util.Log

import com.lazylee.lzywanandroid.data.entity.User
import com.lazylee.lzywanandroid.net.Api
import com.lazylee.lzywanandroid.net.ServiceResult
import com.lazylee.lzywanandroid.net.WanAndroidService
import com.lazylee.lzywanandroid.tools.Logger

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 登录presenter， 用于对登录界面中的数据和网络进行操作
 * Created by lazylee on 2018/3/16.
 */

class LoginPresenter(private val mLoginView: LoginContract.View) : LoginContract.Presenter {

    init {
        mLoginView.setPresenter(this)
    }

    override fun login(userName: String, password: String) {
        mLoginView.enableLoginButton(false)
        var cancelLogin = false
        if (TextUtils.isEmpty(userName)) {
            mLoginView.showUsernameError("用户名不能为空")
            cancelLogin = true
        }

        if (TextUtils.isEmpty(password)) {
            mLoginView.showPasswordError("密码不能为空")
            cancelLogin = true
        }
        if (cancelLogin) {
            Log.d(TAG, "login: username or password is empty")
            mLoginView.enableLoginButton(true)
        } else {
            Log.d(TAG, "login: is login ")
            mLoginView.showProgressBar(true)

            val retrofit = Retrofit.Builder()
                    .baseUrl(Api.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            val wanAndroidService = retrofit.create<WanAndroidService>(WanAndroidService::class.java!!)
            wanAndroidService.login(userName, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<ServiceResult<User>> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(userServiceResult: ServiceResult<User>) {
                            if (userServiceResult.errorCode < 0) {
                                mLoginView.showProgressBar(false)
                                mLoginView.enableLoginButton(true)
                                mLoginView.showMessage(userServiceResult.errorMsg)
                            }
                        }

                        override fun onError(e: Throwable) {
                            mLoginView.showMessage(e.message.toString())
                            mLoginView.showProgressBar(false)

                        }

                        override fun onComplete() {
                            Logger.d("onComplete")
                            mLoginView.showProgressBar(false)
                        }
                    })
        }


    }

    companion object {

        private val TAG = "LoginPresenter"
    }
}
