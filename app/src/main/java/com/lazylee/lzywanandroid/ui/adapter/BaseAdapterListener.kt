package com.lazylee.lzywanandroid.ui.adapter

import android.view.View


interface BaseAdapterListener {


    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onLongClick(view: View, position: Int): Boolean
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener)
    fun setItemLongClickListener(itemLongClickListener: OnItemLongClickListener)
}
