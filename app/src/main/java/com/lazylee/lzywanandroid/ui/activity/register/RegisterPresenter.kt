package com.lazylee.lzywanandroid.ui.activity.register

import android.text.TextUtils

import com.lazylee.lzywanandroid.data.entity.User
import com.lazylee.lzywanandroid.net.Api
import com.lazylee.lzywanandroid.net.ServiceResult
import com.lazylee.lzywanandroid.net.WanAndroidService

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * register presenter
 * Created by lazylee on 2018/3/22.
 */

class RegisterPresenter(private val mRegisterView: RegisterContract.View) : RegisterContract.Presenter {

    init {
        mRegisterView.setPresenter(this)
    }

    override fun register(username: String, password: String, repassword: String) {
        mRegisterView.enableRegisterButton(false)
        var cancel = false
        if (TextUtils.isEmpty(username)) {
            mRegisterView.showUserNameError("用户名不能为空")
            cancel = true
        }
        if (TextUtils.isEmpty(password)) {
            mRegisterView.showPasswordError("密码不能为空")
            cancel = true
        }
        if (TextUtils.isEmpty(repassword)) {
            mRegisterView.showRepasswordError("密码不能为空")
            cancel = true
        }
        if (!cancel && password != repassword) {
            mRegisterView.showRepasswordError("前后密码不一致")
            cancel = true
        }

        if (cancel) {
            mRegisterView.enableRegisterButton(true)
        } else {
            mRegisterView.showProgressBar(true)
            val retrofit = Retrofit.Builder()
                    .baseUrl(Api.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            val wanAndroidService = retrofit.create<WanAndroidService>(WanAndroidService::class.java!!)
            wanAndroidService.register(username, password, repassword)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<ServiceResult<User>> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(userServiceResult: ServiceResult<User>) {
                            val user = userServiceResult.data
                        }

                        override fun onError(e: Throwable) {

                        }

                        override fun onComplete() {

                        }
                    })

        }

    }

    companion object {

        private val TAG = "RegisterPresenter"
    }
}
