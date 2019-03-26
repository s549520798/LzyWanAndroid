package com.lazylee.lzywanandroid.ui.activity.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;



import android.os.Bundle;


import com.google.android.material.textfield.TextInputLayout;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.ui.activity.register.RegisterActivity;
import com.lazylee.lzywanandroid.ui.view.LzyToast;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private LoginContract.Presenter mLoginPresenter;
    private Toolbar mToolbar;
    private NestedScrollView mScrollView;
    private TextInputLayout mUserNameView;
    private TextInputLayout mPasswordView;
    private Button mBtnLogin;
    private TextView mTvRegister;
    private ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();
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
    public void showMessage(String msg, int type) {
        LzyToast.showToast(msg, type);
    }

    @Override
    public void showMessage(String msg) {
        LzyToast.showMessage(msg);
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

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        mScrollView = findViewById(R.id.nestedScrollView);
        mUserNameView = findViewById(R.id.textInputLayout);
        mPasswordView = findViewById(R.id.textInputLayout2);
        mBtnLogin = findViewById(R.id.button_login);
        mTvRegister = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);
    }


}
