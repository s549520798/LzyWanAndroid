package com.lazylee.lzywanandroid.activity.register;

import android.text.TextUtils;

import com.lazylee.lzywanandroid.data.entity.User;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * register presenter
 * Created by lazylee on 2018/3/22.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private static final String TAG = "RegisterPresenter";
    private RegisterContract.View mRegisterView;

    public RegisterPresenter(RegisterContract.View registerView) {
        this.mRegisterView = registerView;
        mRegisterView.setPresenter(this);
    }

    @Override
    public void register(String username, String password, String repassword) {
        mRegisterView.enableRegisterButton(false);
        boolean cancel = false;
        if (TextUtils.isEmpty(username)) {
            mRegisterView.showUserNameError("用户名不能为空");
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            mRegisterView.showPasswordError("密码不能为空");
            cancel = true;
        }
        if (TextUtils.isEmpty(repassword)) {
            mRegisterView.showRepasswordError("密码不能为空");
            cancel = true;
        }
        if (!cancel && !password.equals(repassword)) {
            mRegisterView.showRepasswordError("前后密码不一致");
            cancel = true;
        }

        if (cancel) {
            mRegisterView.enableRegisterButton(true);
        } else {
            mRegisterView.showProgressBar(true);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
            wanAndroidService.register(username,password,repassword)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ServiceResult<User>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ServiceResult<User> userServiceResult) {
                            User user = userServiceResult.getData();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }

    }
}
