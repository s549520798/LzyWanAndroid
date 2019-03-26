package com.lazylee.lzywanandroid.ui.adapter.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.data.entity.SearchHistory;



public class SearchHistoryViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "SearchHistoryViewHolder";
    private TextView mKey;
    private TextView mDate;

    public SearchHistoryViewHolder(View itemView) {
        super(itemView);
        mKey = itemView.findViewById(R.id.search_history_item_key);
        mDate = itemView.findViewById(R.id.search_history_item_date);
    }

    public void bindView(SearchHistory searchHistory){
        mKey.setText(searchHistory.getKey());
        mDate.setText(searchHistory.getDate() + "");
    }
}
