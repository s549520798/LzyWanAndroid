package com.lazylee.lzywanandroid.ui.base

import android.os.Bundle
import android.util.Log

import com.lazylee.lzywanandroid.BuildConfig
import com.lazylee.lzywanandroid.ui.view.LzyToast
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity() {
    private val needTrace = BuildConfig.DEBUG

    private//TODO 判断是否登录状态
    val isLogin: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (needTrace) {
            //TODO trace
            Log.i(TAG, "onCreate: ")
        }
    }

    override fun onStart() {
        super.onStart()
        if (needTrace) {
            Log.i(TAG, "onStart: ")
        }
    }

    override fun onStop() {
        if (needTrace) {
            Log.i(TAG, "onStop: ")
        }

        super.onStop()
    }

    override fun onDestroy() {
        if (needTrace) {
            Log.i(TAG, "onDestroy: ")
        }

        super.onDestroy()
    }

    private fun showMessage(msg: String, type: Int) {
        LzyToast.showToast(msg, type)
    }

    companion object {
        private val TAG = BaseActivity::class.java!!.getSimpleName()
    }
}
