package com.lazylee.lzywanandroid.tools


import android.util.Log

import com.lazylee.lzywanandroid.BuildConfig

import java.util.Arrays

/**
 * 可以根据是否在debug模式判断打印log信息
 */
object Logger {

    private val TAG = "Logger"
    private val DEBUG = BuildConfig.DEBUG

    private val currentMethodName: String
        get() {
            try {
                return Thread.currentThread().stackTrace[4].methodName + "()"
            } catch (ignored: Exception) {
            }

            return TAG
        }

    private val currentClassName: String
        get() {
            try {
                var className = Thread.currentThread().stackTrace[4].className
                val temp = className.split("[.]".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                className = temp[temp.size - 1]
                return className
            } catch (ignored: Exception) {
            }

            return TAG
        }

    fun e(tag: String, text: Any) {
        if (!DEBUG) return
        Log.e(tag, text?.toString() ?: "LOGGER IS NULL")//avoid null
    }

    fun d(tag: String, text: Any?) {
        if (!DEBUG) return
        Log.d(tag, text?.toString() ?: "LOGGER IS NULL")//avoid null
    }

    fun i(tag: String, text: Any?) {
        if (!DEBUG) return
        Log.i(tag, text?.toString() ?: "LOGGER IS NULL")//avoid null
    }

    fun d(text: Any?) {
        d("$currentClassName || $currentMethodName", text)//avoid null
    }

    fun i(text: Any?) {
        i("$currentClassName || $currentMethodName", text)//avoid null
    }

    fun e(vararg objects: Any) {
        if (objects != null && objects.size > 0) {
            e("$currentClassName || $currentMethodName", Arrays.toString(objects))
        } else {
            e("$currentClassName || $currentMethodName", currentMethodName)
        }
    }

    fun e(objects: List<Any>?) {
        if (objects != null) {
            e("$currentClassName || $currentMethodName", Arrays.toString(objects.toTypedArray()))
        } else {
            e(TAG)
        }
    }
}
