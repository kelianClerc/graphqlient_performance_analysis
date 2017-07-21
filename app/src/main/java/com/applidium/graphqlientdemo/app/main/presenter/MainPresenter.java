package com.applidium.graphqlientdemo.app.main.presenter;

import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.main.navigator.MainNavigator;
import com.applidium.graphqlientdemo.app.main.ui.MainViewContract;

import javax.inject.Inject;

public class MainPresenter extends Presenter<MainViewContract> {

    private final MainNavigator navigator;

    @Inject MainPresenter(MainViewContract view, MainNavigator navigator) {
        super(view);
        this.navigator = navigator;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public void onProfile() {
        navigator.navigateToProfile();
    }

    public void onUserList() {
        navigator.navigateToUserList();
    }

    public void onActions() {
        navigator.navigateToActions();
    }
}
