package com.applidium.graphqlientdemo.app.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.app.settings.presenter.SettingsPresenter;
import com.applidium.graphqlientdemo.app.settings.ui.SettingsViewContract;
import com.applidium.graphqlientdemo.di.ComponentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity implements SettingsViewContract {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rest) RadioButton rest;
    @BindView(R.id.graphql) RadioButton graphql;
    @BindView(R.id.radio_group) RadioGroup radioGroup;
    @BindView(R.id.root_parent) ViewGroup root;

    @Inject SettingsPresenter presenter;

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void injectDependencies() {
        ComponentManager.getSettingsComponent(this, this).inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupListeners();
    }

    private void setupView() {
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    private void setupListeners() {
        toolbar.setNavigationOnClickListener(getNavigationListener());
        rest.setOnCheckedChangeListener(getOnCheckChangeListener());
    }

    private CompoundButton.OnCheckedChangeListener getOnCheckChangeListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.onSourceSet(isChecked);
            }
        };
    }

    private View.OnClickListener getNavigationListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
    }

    @Override
    public void showSetError(String errorMessage) {
        Snackbar.make(root, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showSetSuccess() {
        Snackbar.make(root, "Source changed successfuly", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showGetError(String errorMessage) {
        Snackbar.make(root, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setDefaultValue(boolean isRestChecked) {
        Snackbar.make(root, "Default value is " + (isRestChecked ? "Rest" : "GraphQL"), Snackbar.LENGTH_SHORT).show();
        checkDefault(isRestChecked);
    }

    private void checkDefault(boolean isRestChecked) {
        rest.setOnCheckedChangeListener(null);
        rest.setChecked(isRestChecked);
        graphql.setChecked(!isRestChecked);
        rest.setOnCheckedChangeListener(getOnCheckChangeListener());
    }
}
