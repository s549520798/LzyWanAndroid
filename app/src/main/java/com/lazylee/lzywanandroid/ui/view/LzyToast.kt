package com.lazylee.lzywanandroid.ui.view


import android.content.Context


import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.lazylee.lzywanandroid.App
import com.lazylee.lzywanandroid.R

/**
 * 全局toast,可以更改toast样式，位置，防止重复出现toast
 * Created by lazylee on 2018/3/16.
 */

class LzyToast {

    private val mContext: Context? = null    //usually is Application or activity;

    companion object {
        private var mToast: Toast? = null
        val TYPE_NORMAL = 0
        val TYPE_ERROR = 1
        val TYPE_ALERT = 2

        private val DEFAULT_TOAST_DURATION = 1500

        /**
         * initialize toast
         *
         * @param context usually is application or activity
         */
        fun initialize(context: Context) {
            mToast = Toast(context)
        }


        private fun setCustomsView(msg: String, type: Int) {
            val inflater = LayoutInflater.from(App.instance)
            var view: View? = null
            when (type) {
                TYPE_NORMAL -> view = inflater.inflate(R.layout.toast_layout, null)
                TYPE_ERROR -> {
                    view = inflater.inflate(R.layout.toast_layout_error, null)
                    val imageView = view!!.findViewById<ImageView>(R.id.toast_image)
                    imageView.setBackgroundResource(R.drawable.ic_error_white_24dp)
                }
                TYPE_ALERT -> {
                    view = inflater.inflate(R.layout.toast_layout_alert, null)
                    val image = view!!.findViewById<ImageView>(R.id.toast_image)
                    image.setBackgroundResource(R.drawable.ic_alert_white_24dp)
                }
                else -> {
                }
            }
            var textView: TextView? = null
            if (view != null) {
                textView = view.findViewById(R.id.toast_text)
                textView!!.text = msg
            }

            mToast!!.view = view
        }

        private fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
            if (mToast == null) {
                mToast = Toast(App.instance)
            }
            mToast!!.setGravity(gravity, xOffset, yOffset)
        }

        @JvmOverloads
        fun showMessage(msg: String, duration: Int = DEFAULT_TOAST_DURATION) {
            setGravity(Gravity.CENTER, 0, 300)
            setCustomsView(msg, TYPE_NORMAL)
            mToast!!.duration = duration
            mToast!!.show()
        }

        fun showToast(msg: String, type: Int) {
            when (type) {
                TYPE_NORMAL -> showMessage(msg)
                TYPE_ERROR -> showError(msg)
                TYPE_ALERT -> showAlert(msg)
                else -> showMessage(msg)
            }

        }

        @JvmOverloads
        fun showError(msg: String, duration: Int = DEFAULT_TOAST_DURATION) {
            setGravity(Gravity.CENTER, 0, 300)
            setCustomsView(msg, TYPE_ERROR)
            mToast!!.duration = duration
            mToast!!.show()
        }

        @JvmOverloads
        fun showAlert(msg: String, duration: Int = DEFAULT_TOAST_DURATION) {
            setGravity(Gravity.CENTER, 0, 300)
            setCustomsView(msg, TYPE_ALERT)
            mToast!!.duration = duration
            mToast!!.show()
        }
    }
}
