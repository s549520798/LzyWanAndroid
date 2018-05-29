package com.lazylee.lzywanandroid.adapter.viewholder;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.data.entity.Article;


/**
 * view holder for article adapter
 * Created by lazylee on 2018/4/11.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "ArticleViewHolder";
    TextView mAuthor;
    TextView mTitle;
    TextView mType;
    TextView mDate;
    private boolean isReaded;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        mAuthor = itemView.findViewById(R.id.article_item_author);
        mTitle = itemView.findViewById(R.id.article_item_title);
        mType = itemView.findViewById(R.id.article_item_type);
        mDate = itemView.findViewById(R.id.article_item_date);
    }

    public void bindView(@NonNull Article item){
        //mTitle.setText(item.getTitle());
        mTitle.setText(HtmlCompat.fromHtml(item.getTitle(),HtmlCompat.FROM_HTML_MODE_COMPACT));
        mAuthor.setText(item.getAuthor());
        mType.setText(String.format("%s/%s", item.getSuperChapterName(), item.getChapterName()));
        mDate.setText(item.getNiceDate());
    }

}
