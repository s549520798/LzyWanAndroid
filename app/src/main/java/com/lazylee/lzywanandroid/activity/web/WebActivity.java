package com.lazylee.lzywanandroid.activity.web;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.lazylee.lzywanandroid.R;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    private static final String TAG = WebActivity.class.getSimpleName();

    private WebView mWebView;

    private void initViews(){
        mWebView = findViewById(R.id.webView);

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
