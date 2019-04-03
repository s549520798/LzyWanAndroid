package com.lazylee.lzywanandroid.ui.activity.register

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color


import android.os.Bundle


import com.google.android.material.textfield.TextInputLayout
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.ProgressBar

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.view.LzyToast


class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    private var mRegisterPresenter: RegisterContract.Presenter? = null

    private var mToolBar: Toolbar? = null
    private var mScrollView: NestedScrollView? = null
    private var mProgressBar: ProgressBar? = null
    private var mUserNameView: TextInputLayout? = null
    private var mPasswordView: TextInputLayout? = null
    private var mRepasswordView: TextInputLayout? = null
    private var mBtnRegister: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        initViews()
        mRegisterPresenter = RegisterPresenter(this)
        mToolBar!!.setTitleTextColor(Color.WHITE)
        setSupportActionBar(mToolBar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(true)
        }
        mBtnRegister!!.setOnClickListener {
            val username = mUserNameView!!.editText!!.text.toString()
            val password = mPasswordView!!.editText!!.text.toString()
            val repassword = mRepasswordView!!.editText!!.text.toString()
            mRegisterPresenter!!.register(username, password, repassword)
        }
    }


    override fun setPresenter(presenter: RegisterContract.Presenter) {
        this.mRegisterPresenter = presenter
    }

    override fun showMessage(msg: String, type: Int) {
        LzyToast.showToast(msg, type)
    }

    override fun showMessage(msg: String) {
        LzyToast.showMessage(msg, 1500)
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

    override fun enableRegisterButton(enable: Boolean) {
        mBtnRegister!!.isEnabled = enable
        mBtnRegister!!.setTextColor(if (enable) Color.WHITE else Color.GRAY)
    }

    override fun showUserNameError(error: String) {
        mUserNameView!!.error = error
    }

    override fun showPasswordError(error: String) {
        mPasswordView!!.error = error
    }

    override fun showRepasswordError(error: String) {
        mRepasswordView!!.error = error
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    private fun initViews() {
        mToolBar = findViewById(R.id.register_toobar)
        mScrollView = findViewById(R.id.register_scroll)
        mProgressBar = findViewById(R.id.register_prgress)
        mUserNameView = findViewById(R.id.register_username)
        mPasswordView = findViewById(R.id.register_password)
        mRepasswordView = findViewById(R.id.register_repassword)
        mBtnRegister = findViewById(R.id.button_register)

    }

    companion object {

        private val TAG = "RegisterActivity"
    }
}
