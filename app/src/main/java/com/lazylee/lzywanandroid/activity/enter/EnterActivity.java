package com.lazylee.lzywanandroid.activity.enter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.activity.login.LoginActivity;
import com.lazylee.lzywanandroid.activity.main.MainActivity;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.enter_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startActivity(new Intent(this, MainActivity.class));
    }
}
