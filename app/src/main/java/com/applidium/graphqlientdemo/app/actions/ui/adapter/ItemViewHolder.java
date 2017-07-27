package com.applidium.graphqlientdemo.app.actions.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.actions.model.ItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ItemViewModel itemViewModel;

    @BindView(R.id.item_name) TextView item;
    @BindView(R.id.duration) TextView duration;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static ItemViewHolder makeHolder(ViewGroup parent) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.partial_item, parent, false);

        return new ItemViewHolder(view);
    }

    public void bind(ItemViewModel itemViewModel) {

        this.itemViewModel = itemViewModel;
        item.setText(itemViewModel.name());
        duration.setText(itemViewModel.duration());
        item.setCompoundDrawablesWithIntrinsicBounds(
            (itemViewModel.isDone() ? R.drawable.ic_item_ok : 0), 0, 0, 0
        );
    }
}
