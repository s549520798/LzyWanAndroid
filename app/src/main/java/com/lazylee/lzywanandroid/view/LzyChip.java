package com.lazylee.lzywanandroid.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * chip holds hot key
 * Created by lazylee on 2018/3/28.
 */

public class LzyChip extends FrameLayout {
    public LzyChip(@NonNull Context context) {
        this(context, null);
    }

    public LzyChip(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LzyChip(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
