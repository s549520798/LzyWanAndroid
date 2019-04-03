package com.lazylee.lzywanandroid.ui.adapter.viewholder


import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.data.entity.Article


/**
 * view holder for article adapter
 * Created by lazylee on 2018/4/11.
 */

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var mAuthor: TextView
    internal var mTitle: TextView
    internal var mType: TextView
    internal var mDate: TextView
    private val isReaded: Boolean = false

    init {
        mAuthor = itemView.findViewById(R.id.article_item_author)
        mTitle = itemView.findViewById(R.id.article_item_title)
        mType = itemView.findViewById(R.id.article_item_type)
        mDate = itemView.findViewById(R.id.article_item_date)
    }

    fun bindView(item: Article) {
        //mTitle.setText(item.getTitle());
        mTitle.text = HtmlCompat.fromHtml(item.title!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
        mAuthor.text = item.author
        mType.text = String.format("%s/%s", item.superChapterName, item.chapterName)
        mDate.text = item.niceDate
    }

    companion object {

        private val TAG = "ArticleViewHolder"
    }

}
