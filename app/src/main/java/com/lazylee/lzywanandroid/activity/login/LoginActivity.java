package com.lazylee.lzywanandroid.activity.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.activity.register.RegisterActivity;
import com.lazylee.lzywanandroid.view.LzyToast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private LoginContract.Presenter mLoginPresenter;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.nestedScrollView) NestedScrollView mScrollView;
    @BindView(R.id.textInputLayout) TextInputLayout mUserNameView;
    @BindView(R.id.textInputLayout2) TextInputLayout mPasswordView;
    @BindView(R.id.button_login) Button mBtnLogin;
    @BindView(R.id.textView) TextView mTvRegister;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        mBtnLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mPasswordView.setPasswordVisibilityToggleEnabled(true);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mLoginPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.button_login:
                String username = mUserNameView.getEditText().getText().toString();
                String password = mPasswordView.getEditText().getText().toString();
                mLoginPresenter.login(username, password);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(String msg) {
        LzyToast.showMessage(msg, 1500);
    }

    @Override
    public void showProgressBar(final boolean show) {

        mScrollView.setVisibility(show ? View.GONE : View.VISIBLE);
        mScrollView.animate().setDuration(200).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mScrollView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressBar.animate().setDuration(200).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showUsernameError(String error) {
        mUserNameView.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        mPasswordView.setError(error);
    }

    @Override
    public void enableLoginButton(boolean enable) {
        mBtnLogin.setEnabled(enable);
        mBtnLogin.setTextColor(enable ? Color.WHITE : Color.GRAY);
    }
}
