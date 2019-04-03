package com.lazylee.lzywanandroid.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.os.Build
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.tools.Logger

/**
 * chip holds hot key
 * Created by lazylee on 2018/3/28.
 */

class LzyChip @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private var mText: String? = null
    private var mBackgroundColor: Int = 0
    private var mTextColor: Int = 0
    private var isCreated: Boolean = false
    private var mTextView: TextView? = null

    init {
        initTypeArray(attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        Log.d(TAG, "onMeasure: ")
        val thisParams = layoutParams
        thisParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        thisParams.height = resources.getDimension(R.dimen.chip_height).toInt()
        this.layoutParams = thisParams
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow: ")
        if (!isCreated) {
            buildView()
        }
    }

    private fun initTypeArray(attrs: AttributeSet?) {
        val a = context.theme
                .obtainStyledAttributes(attrs, R.styleable.LzyChip, 0, 0)
        mText = a.getString(R.styleable.LzyChip_setText)
        mBackgroundColor = a.getColor(R.styleable.LzyChip_setBackgroundColor,
                ContextCompat.getColor(context, R.color.colorChipBackground))
        mTextColor = a.getColor(R.styleable.LzyChip_setTextColor,
                ContextCompat.getColor(context, R.color.colorChipText))
        a.recycle()
    }

    private fun buildView() {
        initBackgroundColor()
        initTextView()
        isCreated = true
    }

    private fun initBackgroundColor() {
        Logger.d(TAG, "initBackgroundColor: ")
        val mRadius = resources.getDimension(R.dimen.chip_radius)
        val bgDrawable = GradientDrawable()
        bgDrawable.shape = GradientDrawable.RECTANGLE
        bgDrawable.cornerRadii = floatArrayOf(mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius)
        bgDrawable.setColor(mBackgroundColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = bgDrawable
        } else {
            setBackgroundDrawable(bgDrawable)
        }
        isClickable = true
    }

    private fun initTextView() {
        Logger.d(TAG, "initText: ")
        if (!ViewCompat.isAttachedToWindow(this)) {
            return
        }

        if (mTextView == null) {
            mTextView = TextView(context)
        }
        val chipTextParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        chipTextParams.gravity = Gravity.CENTER
        @Px val margin = resources.getDimension(R.dimen.chip_icon_horizontal_margin).toInt()
        @Px val padding = resources.getDimension(R.dimen.chip_horizontal_padding).toInt()
        chipTextParams.setMargins(
                padding,
                0,
                padding,
                0
        )
        mTextView!!.layoutParams = chipTextParams
        mTextView!!.setSingleLine(true)
        mTextView!!.setTextColor(mTextColor)
        mTextView!!.text = mText
        Logger.d(TAG, "initText: add text view")
        this.removeView(mTextView)
        this.addView(mTextView)
    }

    fun setChipText(text: String) {
        mText = text
        //mTextView.setText(mText);  不能在这里设置setText，此时mTextView还为空。
    }

    fun setChipText(@StringRes id: Int) {
        mText = resources.getString(id)
        //mTextView.setText(mText);
    }

    companion object {

        private val TAG = LzyChip::class.java!!.getSimpleName()
    }


}
