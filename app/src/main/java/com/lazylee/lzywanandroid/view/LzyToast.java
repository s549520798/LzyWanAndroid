package com.lazylee.lzywanandroid.view;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_ERROR = 1;
    private static final int TYPE_ALERT = 2;

    /**
     * initialize toast
     *
     * @param context usually is application or activity
     */
    public static void initialize(Context context) {
        mToast = new Toast(context);
    }


    private static void setCustomsView(String msg, int type) {
        LayoutInflater inflater = LayoutInflater.from(App.getInstance().getContext());
        View view = null;
        switch (type) {
            case TYPE_NORMAL:
                view = inflater.inflate(R.layout.toast_layout, null);
                break;
            case TYPE_ERROR:
                view = inflater.inflate(R.layout.toast_layout_error, null);
                ImageView imageView = view.findViewById(R.id.toast_image);
                imageView.setBackgroundResource(R.drawable.ic_error_white_24dp);
                break;
            case TYPE_ALERT:
                view = inflater.inflate(R.layout.toast_layout_alert, null);
                ImageView image = view.findViewById(R.id.toast_image);
                image.setBackgroundResource(R.drawable.ic_warning_white_24dp);
                break;
            default:
                break;
        }
        TextView textView = null;
        if (view != null) {
            textView = view.findViewById(R.id.toast_text);
            textView.setText(msg);
        }

        mToast.setView(view);
    }

    private static void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast == null) {
            mToast = new Toast(App.getInstance().getContext());
        }
        mToast.setGravity(gravity, xOffset, yOffset);
    }

    public static void showMessage(String msg, int duration) {
        setGravity(Gravity.CENTER, 0, 300);
        setCustomsView(msg, TYPE_NORMAL);
        mToast.setDuration(duration);
        mToast.show();
    }

    public static void showError(String msg, int duration) {
        setGravity(Gravity.CENTER, 0, 300);
        setCustomsView(msg, TYPE_ERROR);
        mToast.setDuration(duration);
        mToast.show();
    }

    public static void showAlert(String msg, int duration) {
        setGravity(Gravity.CENTER, 0, 300);
        setCustomsView(msg, TYPE_ALERT);
        mToast.setDuration(duration);
        mToast.show();
    }
}
