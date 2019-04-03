package com.lazylee.lzywanandroid

import android.app.Application
import android.content.Context

import com.lazylee.lzywanandroid.ui.view.LzyToast


/**
 * application
 * Created by lazylee on 2018/3/22.
 */

class App : Application() {

    val context: Context
        get() = applicationContext

    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
        LzyToast.initialize(instance!!.context)
    }

    companion object {

        @Volatile
        var instance: App? = null
            private set
    }


}
