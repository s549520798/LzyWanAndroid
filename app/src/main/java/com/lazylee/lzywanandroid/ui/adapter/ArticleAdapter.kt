package com.lazylee.lzywanandroid.ui.adapter


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.adapter.viewholder.ArticleViewHolder
import com.lazylee.lzywanandroid.data.entity.Article

import java.util.ArrayList


/**
 * adapter for article in home fragment
 * Created by lazylee on 2018/4/11.
 */

class ArticleAdapter(private var mList: ArrayList<Article>?) : RecyclerView.Adapter<ArticleViewHolder>(), BaseAdapterListener {

    private var mItemClickListener: BaseAdapterListener.OnItemClickListener? = null
    private var mItemLongClickListener: BaseAdapterListener.OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_home_article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.itemView.setOnClickListener { view -> mItemClickListener!!.onClick(view, position) }
        holder.itemView.setOnLongClickListener { view -> mItemLongClickListener!!.onLongClick(view, position) }

        holder.bindView(mList!![position])
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    /**
     * 更新文章列表，将新文化在那个添加到文章列表的前部分
     *
     * @param articles list
     */
    fun updateArticles(articles: List<Article>) {
        if (mList == null) {
            mList = ArrayList()
            mList!!.addAll(articles)
        } else {
            for (article in articles) {
                if (!mList!!.contains(article)) mList!!.add(0, article)
            }
        }
        notifyDataSetChanged()
    }

    /**
     * 将原列表清空，添加新的文章，
     *
     * @param articles list
     */
    fun initArticles(articles: List<Article>) {
        if (mList == null) {
            mList = ArrayList()
            mList!!.addAll(articles)
        } else {
            mList!!.clear()
            mList!!.addAll(articles)
        }
        notifyDataSetChanged()
    }

    fun addArticle(article: Article) {
        if (mList != null) {
            mList!!.add(article)
            notifyDataSetChanged()
        } else {
            mList = ArrayList()
            mList!!.add(article)
            notifyDataSetChanged()
        }
    }

    /**
     * 加载更多
     *
     * @param articles list
     */
    fun loadMoreArticles(articles: List<Article>) {
        if (mList != null) {
            if (mList!!.addAll(articles)) notifyDataSetChanged()
        } else {
            mList = ArrayList()
            if (mList!!.addAll(articles)) notifyDataSetChanged()
        }
    }

    fun addFooterView() {

    }


    override fun setItemClickListener(itemClickListener: (View, Int) -> Unit) {
        this.mItemClickListener = itemClickListener
    }

    override fun setItemLongClickListener(itemLongClickListener: BaseAdapterListener.OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }


}
