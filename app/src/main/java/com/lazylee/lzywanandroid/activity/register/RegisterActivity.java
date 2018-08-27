package com.lazylee.lzywanandroid.activity.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;



import android.os.Bundle;


import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.view.LzyToast;


public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private static final String TAG = "RegisterActivity";
    private RegisterContract.Presenter mRegisterPresenter;

    private Toolbar mToolBar;
    private NestedScrollView mScrollView;
    private ProgressBar mProgressBar;
    private TextInputLayout mUserNameView;
    private TextInputLayout mPasswordView;
    private TextInputLayout mRepasswordView;
    private Button mBtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initViews();
        mRegisterPresenter = new RegisterPresenter(this);
        mToolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameView.getEditText().getText().toString();
                String password = mPasswordView.getEditText().getText().toString();
                String repassword = mRepasswordView.getEditText().getText().toString();
                mRegisterPresenter.register(username, password, repassword);
            }
        });
    }


    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mRegisterPresenter = presenter;
    }

    @Override
    public void showMessage(String msg, int type) {
        LzyToast.showToast(msg, type);
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
    public void enableRegisterButton(boolean enable) {
        mBtnRegister.setEnabled(enable);
        mBtnRegister.setTextColor(enable ? Color.WHITE : Color.GRAY);
    }

    @Override
    public void showUserNameError(String error) {
        mUserNameView.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        mPasswordView.setError(error);
    }

    @Override
    public void showRepasswordError(String error) {
        mRepasswordView.setError(error);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    private void initViews() {
        mToolBar = findViewById(R.id.register_toobar);
        mScrollView = findViewById(R.id.register_scroll);
        mProgressBar = findViewById(R.id.register_prgress);
        mUserNameView = findViewById(R.id.register_username);
        mPasswordView = findViewById(R.id.register_password);
        mRepasswordView = findViewById(R.id.register_repassword);
        mBtnRegister = findViewById(R.id.button_register);

    }
}
