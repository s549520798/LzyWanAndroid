package com.lazylee.lzywanandroid.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Environment
import android.view.View
import android.view.inputmethod.InputMethodManager

import com.lazylee.lzywanandroid.App

import java.io.File


/**
 * 获取设备状态，如连接网路等
 */
object StateHelper {
    /**
     * 获取网络连接状态。
     *
     * @return 是否连接网络
     */
    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = App.instance!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

    /**
     * 查看设备是否是使用WiFi 连接
     *
     * @param context 上下文
     * @return 是否WiFi连接
     */
    fun isWifiNetworkUsed(context: Context): Boolean {
        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * 查看设备是否移动数据连接
     *
     * @param context 上下文
     * @return 是否移动数据连接
     */
    fun isMobleNetworkUsed(context: Context): Boolean {
        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * 根据view来显示软件盘
     *
     * @param view view
     */
    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(
                    Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    /**
     * hide softboard
     *
     * @param view view
     */
    fun hideSoftKeyboard(view: View) {
        if (view.isFocused) {
            val imm = view.context.getSystemService(
                    Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 获取应用本地缓存文件夹
     * @param context  上下文
     * @param uniqueName 缓存文件夹类型名
     * @return 缓存问价目录
     */
    fun getDiskCacheDir(context: Context, uniqueName: String): File {
        val cachePath: String
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable()) {
            cachePath = context.externalCacheDir!!.path
        } else {
            cachePath = context.cacheDir.path
        }
        return File(cachePath + File.separator + uniqueName)
    }

}
