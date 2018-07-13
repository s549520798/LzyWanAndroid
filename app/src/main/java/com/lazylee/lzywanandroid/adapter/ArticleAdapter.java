package com.lazylee.lzywanandroid.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.activity.web.WebActivity;
import com.lazylee.lzywanandroid.adapter.viewholder.ArticleViewHolder;
import com.lazylee.lzywanandroid.data.entity.Article;

import java.util.ArrayList;
import java.util.List;


/**
 * adapter for article in home fragment
 * Created by lazylee on 2018/4/11.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {


    private ArrayList<Article> mList;

    public ArticleAdapter(ArrayList<Article> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycle_home_article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("link", mList.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
        holder.bindView(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 更新文章列表，将新文化在那个添加到文章列表的前部分
     *
     * @param articles list
     */
    public void updateArticles(@NonNull List<Article> articles) {
        if (mList == null) {
            mList = new ArrayList<>();
            mList.addAll(articles);
        } else {
            for (Article article : articles) {
                if (!mList.contains(article)) mList.add(0, article);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 将原列表清空，添加新的文章，
     *
     * @param articles list
     */
    public void initArticles(@NonNull List<Article> articles) {
        if (mList == null) {
            mList = new ArrayList<>();
            mList.addAll(articles);
        } else {
            mList.clear();
            mList.addAll(articles);
        }
        notifyDataSetChanged();
    }

    public void addArticle(@NonNull Article article) {
        if (mList != null) {
            mList.add(article);
            notifyDataSetChanged();
        } else {
            mList = new ArrayList<>();
            mList.add(article);
            notifyDataSetChanged();
        }
    }

    /**
     * 加载更多
     *
     * @param articles list
     */
    public void loadMoreArticles(@NonNull List<Article> articles) {
        if (mList != null) {
            if (mList.addAll(articles)) notifyDataSetChanged();
        } else {
            mList = new ArrayList<>();
            if (mList.addAll(articles)) notifyDataSetChanged();
        }
    }
}
