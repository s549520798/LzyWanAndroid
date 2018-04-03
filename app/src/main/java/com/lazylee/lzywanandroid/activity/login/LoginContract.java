package com.lazylee.lzywanandroid.activity.login;

import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

/**
 * 登录分离
 * Created by lazylee on 2018/3/16.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void showUsernameError(String error);

        void showPasswordError(String error);

        void enableLoginButton(boolean enable);

    }

    interface Presenter extends BasePresenter{

        void login(String userName,String password);

    }
}
