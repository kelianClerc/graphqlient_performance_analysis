package com.applidium.graphqlientdemo.app.users.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.applidium.graphqlientdemo.app.users.model.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final List<UserViewModel> dataSet = new ArrayList<>();
    private UserClickedListener listener;

    public UsersAdapter(UserClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UserViewHolder.makeHolder(parent);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(dataSet.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface UserClickedListener {
        void onUserClicked(UserViewModel user);
    }

    public void setContent(List<UserViewModel> users) {
        dataSet.clear();
        dataSet.addAll(users);
        notifyDataSetChanged();
    }
}
