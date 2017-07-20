package com.applidium.graphqlientdemo.app.actions.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;

public class ActionAdapter extends RecyclerView.Adapter<ActionViewHolder> {

    private ActionClickedListener listener;

    public ActionAdapter(ActionClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO (kelianclerc) 20/7/17
        return null;
    }

    @Override
    public void onBindViewHolder(ActionViewHolder holder, int position) {
        // TODO (kelianclerc) 20/7/17
    }

    @Override
    public int getItemCount() {
        // TODO (kelianclerc) 20/7/17
        return 0;
    }

    public interface ActionClickedListener {
        void onActionClicked(ActionViewModel action);
    }
}
