package com.applidium.graphqlientdemo.app.example.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.Html;
import android.widget.TextView;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.app.example.presenter.ExamplePresenter;
import com.applidium.graphqlientdemo.app.example.ui.ExampleViewContract;
import com.applidium.graphqlientdemo.di.ComponentManager;
import com.applidium.graphqlientdemo.utils.theme.ThemeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExampleActivity extends BaseActivity implements ExampleViewContract {

    @Inject ExamplePresenter presenter;
    @BindView(R.id.readme) TextView readMe;

    @Override
    protected void injectDependencies() {
        ComponentManager.getExampleComponent(this).inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtils.ensureRuntimeTheme(this);
        super.onCreate(savedInstanceState);
        setupView();
    }

    private void setupView() {
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override public void showMessage(@StringRes int message) {
        readMe.setText(Html.fromHtml(getString(message)));
    }
}
