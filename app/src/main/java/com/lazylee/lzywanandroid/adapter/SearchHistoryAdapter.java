package com.lazylee.lzywanandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.adapter.viewholder.SearchHistoryViewHolder;
import com.lazylee.lzywanandroid.data.entity.Page;
import com.lazylee.lzywanandroid.data.entity.SearchHistory;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryViewHolder> {

    private List<SearchHistory> historyList = new ArrayList<>();

    public SearchHistoryAdapter() {

    }

    @Override
    public SearchHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycle_search_history, parent, false);
        return new SearchHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHistoryViewHolder holder, int position) {
        holder.bindView(historyList.get(position));
    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }


    public void addHistory(SearchHistory history) {
        historyList.add(history);
        notifyDataSetChanged();
    }

    public void addHistories(List<SearchHistory> histories) {
        historyList.addAll(histories);
    }
    public void updateHistories(List<SearchHistory> histories){
        if (historyList != null){
            historyList.clear();
            historyList.addAll(histories);
        }else {
            historyList = new ArrayList<>();
            historyList.addAll(histories);
        }
        notifyDataSetChanged();
    }
    public void clearAllHistories() {
        historyList.clear();
        notifyDataSetChanged();
    }

    public void deleteHistory(SearchHistory history) {
        //historyList.indexOf(history);
        historyList.remove(history);
        notifyDataSetChanged();
    }
}
