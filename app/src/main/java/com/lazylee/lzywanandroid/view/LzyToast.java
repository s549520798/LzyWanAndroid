package com.lazylee.lzywanandroid.view;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lazylee.lzywanandroid.App;
import com.lazylee.lzywanandroid.R;

/**
 * 全局toast,可以更改toast样式，位置，防止重复出现toast
 * Created by lazylee on 2018/3/16.
 */

public class LzyToast {

    private Context mContext;    //usually is Application or activity;
    private static Toast mToast;

    /**
     * initialize toast
     *
     * @param context usually is application or activity
     */
    public static void initialize(Context context) {
        mToast = new Toast(context);
    }

    public static void showMessage(String msg, int duration) {
        setGravity(Gravity.CENTER,0,300);
        setCustomsView(R.layout.toast_layout, msg);
        mToast.setDuration(duration);
        mToast.show();
    }

    private static void setCustomsView(@LayoutRes int layoutId, String msg) {
        View view = LayoutInflater.from(App.getInstance().getContext()).inflate(layoutId, null);
        TextView textView = (TextView) view.findViewById(R.id.toast_text);
        textView.setText(msg);
        mToast.setView(view);
    }

    private static void setGravity(int gravity, int xOffset, int yOffset){
        if (mToast == null) {
            mToast = new Toast(App.getInstance().getContext());
        }
        mToast.setGravity(gravity,xOffset,yOffset);
    }

}
