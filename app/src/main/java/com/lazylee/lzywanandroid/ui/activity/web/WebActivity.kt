package com.lazylee.lzywanandroid.ui.activity.web


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import com.lazylee.lzywanandroid.R


class WebActivity : AppCompatActivity() {

    private var mWebView: WebView? = null

    private val jsEnable = true

    private fun initViews() {
        mWebView = findViewById(R.id.webView)
        setDefaultWebSetting(mWebView!!)
        mWebView!!.webViewClient = WebViewClient()
        mWebView!!.webChromeClient = WebChromeClient()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity)
        val intent = intent
        val link = intent.getStringExtra("link")

        initViews()
        mWebView!!.loadUrl(link)
    }

    private fun setDefaultWebSetting(webView: WebView) {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = jsEnable
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        //允许js代码
        webSettings.javaScriptEnabled = jsEnable
        //允许SessionStorage/LocalStorage存储
        webSettings.domStorageEnabled = true
        //禁用放缩
        webSettings.displayZoomControls = false
        webSettings.builtInZoomControls = false
        //禁用文字缩放
        webSettings.textZoom = 100
        //10M缓存，api 18后，系统自动管理。
        webSettings.setAppCacheMaxSize((10 * 1024 * 1024).toLong())
        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true)
        //允许WebView使用File协议
        webSettings.allowFileAccess = true
        //不保存密码
        webSettings.savePassword = false
        //自动加载图片
        webSettings.loadsImagesAutomatically = true
    }

    companion object {

        private val TAG = WebActivity::class.java!!.getSimpleName()
    }
}
