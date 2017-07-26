package com.applidium.graphqlientdemo.app.actions.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.actions.ui.ActionDetailViewContract;
import com.applidium.graphqlientdemo.app.actions.ui.adapter.ItemAdapter;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.di.ComponentManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class ActionDetailActivity extends BaseActivity implements ActionDetailViewContract {

    public static final String EXTRA_ACTION_ID = "EXTRA_ACTION_ID";
    @BindView(R.id.recycler) RecyclerView recyclerView;
    private ItemAdapter adapter;

    public static Intent makeIntent(Context context, String actionId) {
        Intent intent = new Intent(context, ActionDetailActivity.class);
        intent.putExtra(EXTRA_ACTION_ID, actionId);
        return intent;
    }

    @Override
    protected void injectDependencies() {
        ComponentManager.getActionDetailComponent(this, this).inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new ItemAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupView() {
        setContentView(R.layout.activity_action);
        ButterKnife.bind(this);
    }
}
