package com.lazylee.lzywanandroid.ui.activity.enter

import android.content.Intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.activity.main.MainActivity

class EnterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.enter_activity)
    }

    override fun onStart() {
        super.onStart()
        //startActivity(new Intent(this, MainActivity.class));
    }
}
