package com.applidium.graphqlientdemo.app.users.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.users.model.UserViewModel;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private UserViewModel userViewModel;
    private UsersAdapter.UserClickedListener listener;

    @BindView(R.id.name) TextView name;
    @BindView(R.id.age) TextView age;
    @BindView(R.id.actions) TextView numberOfActions;
    @BindView(R.id.view_group) ViewGroup viewGroup;

    public static UserViewHolder makeHolder(ViewGroup parent) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.partial_user, parent, false);

        return new UserViewHolder(view);
    }

    public UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(UserViewModel userViewModel, UsersAdapter.UserClickedListener listener) {
        this.userViewModel = userViewModel;
        this.listener = listener;

        name.setText(userViewModel.name());
        age.setText(userViewModel.age());
        numberOfActions.setText(userViewModel.numberOfActions());
        viewGroup.setOnClickListener(getOnUserClickedListener());
    }

    private View.OnClickListener getOnUserClickedListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onUserClicked(userViewModel);
                }
            }
        };
    }
}
