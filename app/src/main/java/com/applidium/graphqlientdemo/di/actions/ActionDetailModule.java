package com.applidium.graphqlientdemo.di.actions;

import com.applidium.graphqlientdemo.app.actions.ui.ActionDetailViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ActionDetailModule {
    private final ActionDetailViewContract viewContract;

    public ActionDetailModule(ActionDetailViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Provides
    ActionDetailViewContract viewContract() {
        return viewContract;
    }
}
