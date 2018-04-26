package com.lazylee.lzywanandroid.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.tools.Logger;

/**
 * chip holds hot key
 * Created by lazylee on 2018/3/28.
 */

public class LzyChip extends FrameLayout {

    private static final String TAG = LzyChip.class.getSimpleName();

    private String mText;
    private int mBackgroundColor;
    private int mTextColor;
    private boolean isCreated;
    private TextView mTextView;

    public LzyChip(@NonNull Context context) {
        this(context, null);
    }

    public LzyChip(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LzyChip(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "onMeasure: ");
        ViewGroup.LayoutParams thisParams = getLayoutParams();
        thisParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        thisParams.height = (int) getResources().getDimension(R.dimen.chip_height);
        this.setLayoutParams(thisParams);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
        if (!isCreated) {
            buildView();
        }
    }

    private void initTypeArray(AttributeSet attrs) {
        TypedArray a = getContext().getTheme()
                .obtainStyledAttributes(attrs, R.styleable.LzyChip, 0, 0);
        mText = a.getString(R.styleable.LzyChip_setText);
        mBackgroundColor = a.getColor(R.styleable.LzyChip_setBackgroundColor,
                ContextCompat.getColor(getContext(), R.color.colorChipBackground));
        mTextColor = a.getColor(R.styleable.LzyChip_setTextColor,
                ContextCompat.getColor(getContext(), R.color.colorChipText));
        a.recycle();
    }

    private void buildView() {
        initBackgroundColor();
        initTextView();
        isCreated = true;
    }

    private void initBackgroundColor() {
        Logger.d(TAG, "initBackgroundColor: ");
        float mRadius = getResources().getDimension(R.dimen.chip_radius);
        GradientDrawable bgDrawable = new GradientDrawable();
        bgDrawable.setShape(GradientDrawable.RECTANGLE);
        bgDrawable.setCornerRadii(new float[]{mRadius, mRadius, mRadius, mRadius,
                mRadius, mRadius, mRadius, mRadius});
        bgDrawable.setColor(mBackgroundColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(bgDrawable);
        } else {
            setBackgroundDrawable(bgDrawable);
        }
        setClickable(true);
    }

    private void initTextView() {
        Logger.d(TAG, "initText: ");
        if (!ViewCompat.isAttachedToWindow(this)) {
            return;
        }

        if (mTextView == null) {
            mTextView = new TextView(getContext());
        }
        FrameLayout.LayoutParams chipTextParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        chipTextParams.gravity = Gravity.CENTER;
        @Px int margin = (int) getResources().getDimension(R.dimen.chip_icon_horizontal_margin);
        @Px int padding = (int) getResources().getDimension(R.dimen.chip_horizontal_padding);
        chipTextParams.setMargins(
                padding,
                0,
                padding,
                0
        );
        mTextView.setLayoutParams(chipTextParams);
        mTextView.setSingleLine(true);
        mTextView.setTextColor(mTextColor);
        mTextView.setText(mText);
        Logger.d(TAG, "initText: add text view");
        this.removeView(mTextView);
        this.addView(mTextView);
    }

    public void setChipText(String text) {
        mText = text;
        //mTextView.setText(mText);  不能在这里设置setText，此时mTextView还为空。
    }

    public void setChipText(@StringRes int id) {
        mText = getResources().getString(id);
        //mTextView.setText(mText);
    }


}
