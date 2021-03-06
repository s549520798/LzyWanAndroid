package com.lazylee.lzywanandroid.ui.activity.login;

import android.text.TextUtils;
import android.util.Log;

import com.lazylee.lzywanandroid.data.entity.User;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;
import com.lazylee.lzywanandroid.tools.log.Logger;
import com.lazylee.lzywanandroid.tools.log.Logger.*;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 登录presenter， 用于对登录界面中的数据和网络进行操作
 * Created by lazylee on 2018/3/16.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";
    private LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View loginView) {
        this.mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void login(final String userName, final String password) {
        mLoginView.enableLoginButton(false);
        boolean cancelLogin = false;
        if (TextUtils.isEmpty(userName)) {
            mLoginView.showUsernameError("用户名不能为空");
            Logger.e(Logger.LOG_MODEL_UI, Logger.LOG_DETAIL_LOGIN, TAG, "login user name is empty");
            cancelLogin = true;
        }

        if (TextUtils.isEmpty(password)) {
            mLoginView.showPasswordError("密码不能为空");
            cancelLogin = true;
        }
        if (cancelLogin) {
            Logger.d(Logger.LOG_MODEL_UI,Logger.LOG_DETAIL_LOGIN,TAG, "login: username or password is empty");
            mLoginView.enableLoginButton(true);
        } else {
            Log.d(TAG, "login: is login ");
            mLoginView.showProgressBar(true);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
            wanAndroidService.login(userName, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ServiceResult<User>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ServiceResult<User> userServiceResult) {
                            if (userServiceResult.getErrorCode() < 0) {
                                mLoginView.showProgressBar(false);
                                mLoginView.enableLoginButton(true);
                                mLoginView.showMessage(userServiceResult.getErrorMsg());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mLoginView.showMessage(e.getMessage());
                            mLoginView.showProgressBar(false);

                        }

                        @Override
                        public void onComplete() {
                            mLoginView.showProgressBar(false);
                        }
                    });
        }


    }
}
