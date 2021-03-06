package com.applidium.graphqlientdemo.app.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.app.main.presenter.MainPresenter;
import com.applidium.graphqlientdemo.app.main.ui.MainViewContract;
import com.applidium.graphqlientdemo.di.ComponentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
    MainViewContract, Toolbar.OnMenuItemClickListener, TabLayout.OnTabSelectedListener {
    private static final int USER_LIST_TAB = 0;
    private static final int LAST_ACTION_TAB = 1;
    private static final int DEFAULT_TAB = USER_LIST_TAB;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    @Inject MainPresenter presenter;

    @Override
    protected void injectDependencies() {
        FragmentManager manager = getSupportFragmentManager();
        ComponentManager.getMainComponent(this, this, manager).inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupToolbar();
        setupTabLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onUserList();
    }

    private void setupView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void setupToolbar() {
        toolbar.setOnMenuItemClickListener(this);
        toolbar.inflateMenu(R.menu.settings);
    }

    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.getTabAt(DEFAULT_TAB).select();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                presenter.onSettings();
                return true;
        }
        return false;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case USER_LIST_TAB:
                presenter.onUserList();
                break;
            case LAST_ACTION_TAB:
                presenter.onActions();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        //no-op
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // TODO (kelianclerc) 20/7/17
    }
}
