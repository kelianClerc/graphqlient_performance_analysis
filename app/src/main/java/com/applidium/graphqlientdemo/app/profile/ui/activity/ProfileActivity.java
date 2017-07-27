package com.applidium.graphqlientdemo.app.profile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;
import com.applidium.graphqlientdemo.app.actions.ui.adapter.ActionAdapter;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.app.profile.model.ProfileViewModel;
import com.applidium.graphqlientdemo.app.profile.presenter.ProfilePresenter;
import com.applidium.graphqlientdemo.app.profile.ui.ProfileViewContract;
import com.applidium.graphqlientdemo.di.ComponentManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class ProfileActivity extends BaseActivity implements
    ProfileViewContract, ActionAdapter.ActionClickedListener
{

    private static final String DEFAULT_USER_ID = "0";
    public static final String EXTRA_USER_ID = "EXTRA_USER_ID";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.firstname) TextView firstName;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.age) TextView age;
    @BindView(R.id.empty) TextView empty;

    private ActionAdapter adapter;
    private String userId = DEFAULT_USER_ID;
    @Inject ProfilePresenter presenter;

    public static Intent makeIntent(Context context) {
        return makeIntent(context, DEFAULT_USER_ID);
    }

    public static Intent makeIntent(Context context, String userId) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

    @Override
    protected void injectDependencies() {
        ComponentManager.getProfileComponent(this, this).inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // FIXME (kelianclerc) 20/7/17 remove
        presenter.onStart(userId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupToolbar();
        setupRecycler();
        setupUserId();
    }

    private void setupUserId() {
        Intent intent = getIntent();
        userId = intent.getStringExtra(EXTRA_USER_ID);
    }

    private void setupView() {
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
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

    private void setupRecycler() {
        adapter = new ActionAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, R.anim.slide_down);
    }

    @Override
    public void onActionClicked(ActionViewModel action) {
        presenter.onAction(action.id());
    }

    @Override
    public void showError() {

    }

    @Override
    public void showData(List<ActionViewModel> model) {
        shouldShowEmpty(model.size() == 0);
        adapter.setContent(model);
    }

    @Override
    public void showProfile(ProfileViewModel viewModel) {
        firstName.setText(viewModel.firstName());
        name.setText(viewModel.name());
        age.setText(viewModel.age());
    }

    public void shouldShowEmpty(Boolean isEmpty) {
        recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        empty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }


}
