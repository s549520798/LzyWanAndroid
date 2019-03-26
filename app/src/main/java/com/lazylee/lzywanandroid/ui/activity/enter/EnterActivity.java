package com.lazylee.lzywanandroid.ui.activity.enter;

import android.content.Intent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.ui.activity.main.MainActivity;

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
