package com.lazylee.lzywanandroid.activity.web;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lazylee.lzywanandroid.R;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    private static final String TAG = WebActivity.class.getSimpleName();

    private WebView mWebView;

    private boolean jsEnable = true;

    private void initViews(){
        mWebView = findViewById(R.id.webView);
        mWebView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(jsEnable);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        initViews();
        mWebView.loadUrl(link);
    }
}
