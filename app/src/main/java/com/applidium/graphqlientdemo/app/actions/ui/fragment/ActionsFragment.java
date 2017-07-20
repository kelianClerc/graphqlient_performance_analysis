package com.applidium.graphqlientdemo.app.actions.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;
import com.applidium.graphqlientdemo.app.actions.ui.ActionViewContract;
import com.applidium.graphqlientdemo.app.actions.ui.adapter.ActionAdapter;
import com.applidium.graphqlientdemo.app.common.BaseFragment;
import com.applidium.graphqlientdemo.di.ComponentManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionsFragment extends BaseFragment implements
    ActionViewContract, ActionAdapter.ActionClickedListener
{

    private ActionAdapter adapter;
    @BindView(R.id.recycler) RecyclerView recyclerView;


    public static ActionsFragment makeFragment() {
        return new ActionsFragment();
    }

    @Override
    protected void injectDependencies() {
        FragmentManager manager = getChildFragmentManager();
        ComponentManager.getActionsComponent(getContext(), this, manager).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_actions, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    private void setupAdapter() {
        adapter = new ActionAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActionClicked(ActionViewModel action) {
        // TODO (kelianclerc) 20/7/17
        Toast.makeText(getContext(), "Action clicked : " + action.articleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showActions(List<ActionViewModel> viewModels) {
        adapter.setContent(viewModels);
    }
}
