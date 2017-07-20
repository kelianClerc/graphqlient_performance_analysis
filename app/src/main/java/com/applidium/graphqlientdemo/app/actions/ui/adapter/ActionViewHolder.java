package com.applidium.graphqlientdemo.app.actions.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.article_name) TextView articleName;
    @BindView(R.id.article_number) TextView numberOfItem;
    @BindView(R.id.item_name) TextView itemName;
    @BindView(R.id.duration) TextView duration;
    @BindView(R.id.action) ViewGroup group;
    private ActionViewModel actionViewModel;
    private ActionAdapter.ActionClickedListener listener;

    public static ActionViewHolder makeHolder(ViewGroup parent) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.partial_action, parent, false);
        return new ActionViewHolder(view);
    }

    public ActionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ActionViewModel actionViewModel, ActionAdapter.ActionClickedListener listener) {
        this.actionViewModel = actionViewModel;
        this.listener = listener;
        articleName.setText(actionViewModel.articleName());
        numberOfItem.setText(actionViewModel.numberOfItems());
        itemName.setText(actionViewModel.lastItemTitle());
        itemName.setCompoundDrawablesWithIntrinsicBounds(
            actionViewModel.isDone() ? R.drawable.ic_done : 0,
            0,
            0,
            0
        );
        duration.setText(actionViewModel.lastItemDuration());
        group.setOnClickListener(getOnActionClicked());
    }

    private View.OnClickListener getOnActionClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onActionClicked(actionViewModel);
                }
            }
        };
    }
}
