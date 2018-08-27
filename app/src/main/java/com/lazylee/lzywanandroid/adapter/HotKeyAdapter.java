package com.lazylee.lzywanandroid.adapter;



import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.adapter.viewholder.HotKeyViewHolder;
import com.lazylee.lzywanandroid.data.entity.HotKey;

import java.util.ArrayList;
import java.util.List;



public class HotKeyAdapter extends RecyclerView.Adapter<HotKeyViewHolder> implements BaseAdapterListener{

    private ArrayList<HotKey> hotKeys = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    private OnItemLongClickListener itemLongClickListener;

    public HotKeyAdapter() {
    }

    @NonNull
    @Override
    public HotKeyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycle_hotkey_item, parent, false);
        return new HotKeyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotKeyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onClick(view,position);
        });
        holder.itemView.setOnLongClickListener(view -> itemLongClickListener.onLongClick(view,position));
        holder.bindView(hotKeys.get(position));
    }

    @Override
    public int getItemCount() {
        return hotKeys == null ? 0 : hotKeys.size();
    }

    public void addHotKey(HotKey hotKey) {
        if (hotKeys != null) {
            if (!hotKeys.contains(hotKey)) hotKeys.add(hotKey);
        } else {
            hotKeys = new ArrayList<>();
            hotKeys.add(hotKey);
        }
        notifyDataSetChanged();
    }

    public void addHotkeys(List<HotKey> list) {
        if (hotKeys != null && !hotKeys.isEmpty()) {
            for (HotKey hotKey : list) {
                if (!hotKeys.contains(hotKey)) hotKeys.add(hotKey);
            }
        } else {
            if (hotKeys == null) hotKeys = new ArrayList<>();
            hotKeys.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }
}
