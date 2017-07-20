package com.applidium.graphqlientdemo.app.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.app.main.ui.MainViewContract;
import com.applidium.graphqlientdemo.di.ComponentManager;

public class MainActivity extends BaseActivity implements MainViewContract {

    @Override
    protected void injectDependencies() {
        ComponentManager.getLoggingComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
    }

    private void setupView() {
        setContentView(R.layout.activity_example);
    }
}
