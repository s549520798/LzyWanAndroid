package com.lazylee.lzywanandroid.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.adapter.viewholder.SearchHistoryViewHolder
import com.lazylee.lzywanandroid.data.entity.SearchHistory

import java.util.ArrayList


class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryViewHolder>(), BaseAdapterListener {

    private var historyList: MutableList<SearchHistory>? = ArrayList()
    private var mItemClickListener: BaseAdapterListener.OnItemClickListener? = null
    private var mItemLongClickListener: BaseAdapterListener.OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_search_history, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.itemView.setOnClickListener { view -> mItemClickListener!!.onClick(view, position) }
        holder.itemView.setOnLongClickListener { view -> mItemLongClickListener!!.onLongClick(view, position) }
        holder.bindView(historyList!![position])
    }

    override fun getItemCount(): Int {
        return if (historyList == null) 0 else historyList!!.size
    }


    fun addHistory(history: SearchHistory) {
        historyList!!.add(history)
        notifyDataSetChanged()
    }

    fun addHistories(histories: List<SearchHistory>) {
        historyList!!.addAll(histories)
    }

    fun updateHistories(histories: List<SearchHistory>) {
        if (historyList != null) {
            historyList!!.clear()
            historyList!!.addAll(histories)
        } else {
            historyList = ArrayList()
            historyList!!.addAll(histories)
        }
        notifyDataSetChanged()
    }

    fun clearAllHistories() {
        historyList!!.clear()
        notifyDataSetChanged()
    }

    fun deleteHistory(history: SearchHistory) {
        //historyList.indexOf(history);
        historyList!!.remove(history)
        notifyDataSetChanged()
    }

    override fun setItemClickListener(itemClickListener: (View, Int) -> Unit) {
        this.mItemClickListener = itemClickListener
    }

    override fun setItemLongClickListener(itemLongClickListener: BaseAdapterListener.OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }
}
