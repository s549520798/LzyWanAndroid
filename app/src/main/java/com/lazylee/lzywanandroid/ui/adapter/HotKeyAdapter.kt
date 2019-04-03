package com.lazylee.lzywanandroid.ui.adapter


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.adapter.viewholder.HotKeyViewHolder
import com.lazylee.lzywanandroid.data.entity.HotKey

import java.util.ArrayList


class HotKeyAdapter : RecyclerView.Adapter<HotKeyViewHolder>(), BaseAdapterListener {

    private var hotKeys: ArrayList<HotKey>? = ArrayList()
    private var itemClickListener: BaseAdapterListener.OnItemClickListener? = null

    private var itemLongClickListener: BaseAdapterListener.OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotKeyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_hotkey_item, parent, false)
        return HotKeyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotKeyViewHolder, position: Int) {
        holder.itemView.setOnClickListener { view -> itemClickListener!!.onClick(view, position) }
        holder.itemView.setOnLongClickListener { view -> itemLongClickListener!!.onLongClick(view, position) }
        holder.bindView(hotKeys!![position])
    }

    override fun getItemCount(): Int {
        return if (hotKeys == null) 0 else hotKeys!!.size
    }

    fun addHotKey(hotKey: HotKey) {
        if (hotKeys != null) {
            if (!hotKeys!!.contains(hotKey)) hotKeys!!.add(hotKey)
        } else {
            hotKeys = ArrayList()
            hotKeys!!.add(hotKey)
        }
        notifyDataSetChanged()
    }

    fun addHotkeys(list: List<HotKey>) {
        if (hotKeys != null && !hotKeys!!.isEmpty()) {
            for (hotKey in list) {
                if (!hotKeys!!.contains(hotKey)) hotKeys!!.add(hotKey)
            }
        } else {
            if (hotKeys == null) hotKeys = ArrayList()
            hotKeys!!.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun setItemClickListener(itemClickListener: (View, Int) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    override fun setItemLongClickListener(itemLongClickListener: BaseAdapterListener.OnItemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener
    }
}
