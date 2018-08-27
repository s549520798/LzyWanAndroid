package com.lazylee.lzywanandroid.view;

import android.content.Context;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.lazylee.lzywanandroid.R;

/**
 * 给SRL设置颜色
 * Created by lazylee on 2018/4/18.
 */

public class AppbarRefreshLayout extends SwipeRefreshLayout {
    public AppbarRefreshLayout(Context context) {
        super(context);
    }

    public AppbarRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeResources(R.color.material_amber_700, R.color.material_blue_700, R.color.material_purple_700, R.color.material_lime_700);
    }
}
