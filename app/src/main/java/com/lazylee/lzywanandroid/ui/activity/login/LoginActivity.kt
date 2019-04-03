package com.lazylee.lzywanandroid.ui.activity.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.Color


import android.os.Bundle


import com.google.android.material.textfield.TextInputLayout
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView


import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.activity.register.RegisterActivity
import com.lazylee.lzywanandroid.ui.view.LzyToast

class LoginActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener {
    private var mLoginPresenter: LoginContract.Presenter? = null
    private var mToolbar: Toolbar? = null
    private var mScrollView: NestedScrollView? = null
    private var mUserNameView: TextInputLayout? = null
    private var mPasswordView: TextInputLayout? = null
    private var mBtnLogin: Button? = null
    private var mTvRegister: TextView? = null
    private var mProgressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        initViews()
        mLoginPresenter = LoginPresenter(this)
        setSupportActionBar(mToolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
        mBtnLogin!!.setOnClickListener(this)
        mTvRegister!!.setOnClickListener(this)
        mPasswordView!!.isPasswordVisibilityToggleEnabled = true
    }


    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.mLoginPresenter = presenter
    }

    override fun showMessage(msg: String, type: Int) {
        LzyToast.showToast(msg, type)
    }

    override fun showMessage(msg: String) {
        LzyToast.showMessage(msg)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.textView -> startActivity(Intent(this, RegisterActivity::class.java))
            R.id.button_login -> {
                val username = mUserNameView!!.editText!!.text.toString()
                val password = mPasswordView!!.editText!!.text.toString()
                mLoginPresenter!!.login(username, password)
            }
            else -> {
            }
        }
    }

    override fun showProgressBar(show: Boolean) {

        mScrollView!!.visibility = if (show) View.GONE else View.VISIBLE
        mScrollView!!.animate().setDuration(200).alpha(
                (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mScrollView!!.visibility = if (show) View.GONE else View.VISIBLE
            }
        })

        mProgressBar!!.visibility = if (show) View.VISIBLE else View.GONE
        mProgressBar!!.animate().setDuration(200).alpha(
                (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mProgressBar!!.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    override fun showUsernameError(error: String) {
        mUserNameView!!.error = error
    }

    override fun showPasswordError(error: String) {
        mPasswordView!!.error = error
    }

    override fun enableLoginButton(enable: Boolean) {
        mBtnLogin!!.isEnabled = enable
        mBtnLogin!!.setTextColor(if (enable) Color.WHITE else Color.GRAY)
    }

    private fun initViews() {
        mToolbar = findViewById(R.id.toolbar)
        mScrollView = findViewById(R.id.nestedScrollView)
        mUserNameView = findViewById(R.id.textInputLayout)
        mPasswordView = findViewById(R.id.textInputLayout2)
        mBtnLogin = findViewById(R.id.button_login)
        mTvRegister = findViewById(R.id.textView)
        mProgressBar = findViewById(R.id.progressBar)
    }

    companion object {

        private val TAG = "LoginActivity"
    }


}
