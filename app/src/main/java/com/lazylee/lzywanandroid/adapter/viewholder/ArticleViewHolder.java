package com.lazylee.lzywanandroid.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.data.entity.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * view holder for article adapter
 * Created by lazylee on 2018/4/11.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.article_item_author)
    TextView mAuthor;
    @BindView(R.id.article_item_title)
    TextView mTitle;
    @BindView(R.id.article_item_type)
    TextView mType;
    @BindView(R.id.article_item_date)
    TextView mDate;
    private boolean isReaded;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindView(@NonNull Article item){
        mTitle.setText(item.getTitle());
        mAuthor.setText(item.getAuthor());
        mType.setText(String.format("%s/%s", item.getSuperChapterName(), item.getChapterName()));
        mDate.setText(item.getNiceDate());
    }

}
