package com.applidium.graphqlientdemo.app.actions.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.applidium.graphqlientdemo.app.actions.model.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private final List<ItemViewModel> dataSet = new ArrayList<>();
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.makeHolder(parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setContent(List<ItemViewModel> data) {
        this.dataSet.clear();
        this.dataSet.addAll(data);
        notifyDataSetChanged();
    }
}
