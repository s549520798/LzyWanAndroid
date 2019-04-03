package com.lazylee.lzywanandroid.ui.adapter.viewholder


import androidx.recyclerview.widget.RecyclerView
import android.view.View

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.data.entity.HotKey
import com.lazylee.lzywanandroid.ui.view.LzyChip


class HotKeyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var chip: LzyChip

    init {
        chip = itemView.findViewById(R.id.hot_key_chip)
    }

    fun bindView(hotKey: HotKey) {
        chip.setChipText(hotKey.name)
    }

    companion object {

        private val TAG = "HotKeyViewHolder"
    }
}
