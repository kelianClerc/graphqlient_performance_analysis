package com.applidium.graphqlientdemo.app.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.app.settings.ui.SettingsViewContract;
import com.applidium.graphqlientdemo.di.ComponentManager;

import butterknife.BindView;

public class SettingsActivity extends BaseActivity implements SettingsViewContract {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rest) RadioButton rest;
    @BindView(R.id.graphql) RadioButton graphql;
    @BindView(R.id.radio_group) RadioGroup radioGroup;

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void injectDependencies() {
        ComponentManager.getLoggingComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupListeners();
    }

    private void setupView() {
        setContentView(R.layout.activity_settings);
    }

    private void setupListeners() {
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
}
