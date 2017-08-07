package com.applidium.graphqlientdemo.app.actions.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;

import java.util.ArrayList;
import java.util.List;

public class ActionAdapter extends RecyclerView.Adapter<ActionViewHolder> {

    private ActionClickedListener listener;
    private final List<ActionViewModel> dataSet = new ArrayList<>();

    public ActionAdapter(ActionClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ActionViewHolder.makeHolder(parent);
    }

    @Override
    public void onBindViewHolder(ActionViewHolder holder, int position) {
        holder.bind(dataSet.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setContent(List<ActionViewModel> model) {
        dataSet.clear();
        dataSet.addAll(model);
        notifyDataSetChanged();
    }

    public interface ActionClickedListener {
        void onActionClicked(ActionViewModel action);
    }
}
