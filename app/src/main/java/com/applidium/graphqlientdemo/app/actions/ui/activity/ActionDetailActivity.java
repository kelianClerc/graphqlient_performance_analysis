package com.applidium.graphqlientdemo.app.actions.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.actions.model.ActionDetailViewModel;
import com.applidium.graphqlientdemo.app.actions.presenter.ActionDetailPresenter;
import com.applidium.graphqlientdemo.app.actions.ui.ActionDetailViewContract;
import com.applidium.graphqlientdemo.app.actions.ui.adapter.ItemAdapter;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.di.ComponentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class ActionDetailActivity extends BaseActivity implements ActionDetailViewContract {

    public static final String EXTRA_ACTION_ID = "EXTRA_ACTION_ID";
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject ActionDetailPresenter presenter;
    private ItemAdapter adapter;
    private String actionId;

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
    protected void onStart() {
        super.onStart();
        presenter.onStart(actionId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupAdapter();
        getActionId();
        setupToolbar();
    }

    private void setupToolbar() {
        toolbar.setNavigationOnClickListener(getNavigationListener());
    }

    private View.OnClickListener getNavigationListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
    }

    private void setupView() {
        setContentView(R.layout.activity_action);
        ButterKnife.bind(this);
    }

    private void setupAdapter() {
        adapter = new ItemAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getActionId() {
        Intent intent = getIntent();
        actionId = intent.getStringExtra(EXTRA_ACTION_ID);
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showDetail(ActionDetailViewModel viewModel) {
        adapter.setContent(viewModel.items());
    }
}
