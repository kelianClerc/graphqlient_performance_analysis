package com.applidium.graphqlientdemo.app.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import butterknife.OnCheckedChanged;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity implements SettingsViewContract {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rest) RadioButton rest;
    @BindView(R.id.graphql) RadioButton graphql;
    @BindView(R.id.radio_group) RadioGroup radioGroup;

    @Inject SettingsPresenter presenter;

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void injectDependencies() {
        ComponentManager.getSettingsComponent(this, this).inject(this);
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
    }

    private View.OnClickListener getNavigationListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
    }

    @OnCheckedChanged({ R.id.rest, R.id.graphql })
    public void onRadio(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            presenter.onSourceSet(rest.isChecked());
        }
    }
}
