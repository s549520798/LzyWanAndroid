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
                Intent intent = new Intent(view.getContext(),WebActivity.class);
                intent.putExtra("link",mList.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
        holder.bindView(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateArticles(@NonNull List<Article> articles) {
        if (mList == null) {
            mList = new ArrayList<>();
            mList.addAll(articles);
        } else {
            mList.clear();
            mList.addAll(articles);
        }
        notifyDataSetChanged();
    }

    public void addArticles(@NonNull List<Article> articles){
        if (mList == null) {
            mList = new ArrayList<>();
            mList.addAll(articles);
        } else {
            mList.addAll(articles);
        }
        notifyDataSetChanged();
    }

    public void addArticle(@NonNull Article article) {
        if (mList != null) {
            mList.add(article);
            notifyDataSetChanged();
        }else {
            mList = new ArrayList<>();
            mList.add(article);
            notifyDataSetChanged();
        }
    }

    public void loadMoreArticles(@NonNull List<Article> articles) {
        if (mList != null) {
            if (mList.addAll(mList.size(), articles)) notifyDataSetChanged();
        }else {
            mList = new ArrayList<>();
            if (mList.addAll(mList.size(), articles)) notifyDataSetChanged();
        }
    }
}
