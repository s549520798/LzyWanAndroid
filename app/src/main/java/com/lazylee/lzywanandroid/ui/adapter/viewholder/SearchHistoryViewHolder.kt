package com.lazylee.lzywanandroid.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.data.entity.SearchHistory


class SearchHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mKey: TextView
    private val mDate: TextView

    init {
        mKey = itemView.findViewById(R.id.search_history_item_key)
        mDate = itemView.findViewById(R.id.search_history_item_date)
    }

    fun bindView(searchHistory: SearchHistory) {
        mKey.text = searchHistory.key
        mDate.text = searchHistory.date!!.toString() + ""
    }

    companion object {

        private val TAG = "SearchHistoryViewHolder"
    }
}
