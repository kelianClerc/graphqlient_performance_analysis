package com.applidium.graphqlientdemo.app.users.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.common.BaseFragment;
import com.applidium.graphqlientdemo.app.common.DividerHorizontalItemDecoration;
import com.applidium.graphqlientdemo.app.users.model.UserViewModel;
import com.applidium.graphqlientdemo.app.users.presenter.UsersPresenter;
import com.applidium.graphqlientdemo.app.users.ui.UsersViewContract;
import com.applidium.graphqlientdemo.app.users.ui.adapter.UsersAdapter;
import com.applidium.graphqlientdemo.di.ComponentManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersFragment extends BaseFragment implements
    UsersViewContract, UsersAdapter.UserClickedListener
{

    @BindView(R.id.recycler) RecyclerView recyclerView;

    private UsersAdapter adapter;

    @Inject UsersPresenter presenter;

    public static UsersFragment makeFragment() {
        return new UsersFragment();
    }

    @Override
    protected void injectDependencies() {
        FragmentManager manager = getChildFragmentManager();
        ComponentManager.getUsersComponent(getContext(), this, manager).inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    private void setupAdapter() {
        adapter = new UsersAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerHorizontalItemDecoration decor = new DividerHorizontalItemDecoration(
            getContext(), R.drawable.padded_horizontal_divider_line);
        recyclerView.addItemDecoration(decor);
    }

    @Override
    public void onUserClicked(UserViewModel user) {
        presenter.onUser(user);
    }

    @Override
    public void showUsers(List<UserViewModel> viewModels) {
        adapter.setContent(viewModels);
    }
}
