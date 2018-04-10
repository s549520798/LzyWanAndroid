package com.lazylee.lzywanandroid.activity.register;

import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

/**
 * register contract
 * Created by lazylee on 2018/3/22.
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {

        void enableRegisterButton(boolean enable);

        void showUserNameError(String error);

        void showPasswordError(String error);

        void showRepasswordError(String error);

        void showProgressBar(boolean show);
    }

    interface Presenter extends BasePresenter {

        void register(String username, String password, String repassword);
    }
}
