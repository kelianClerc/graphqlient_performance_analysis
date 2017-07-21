package com.applidium.graphqlientdemo.di.actions;

import com.applidium.graphqlientdemo.app.actions.ui.ActionViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ActionsModule {

    private final ActionViewContract viewContract;

    public ActionsModule(ActionViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Provides
    ActionViewContract viewContract() {
        return viewContract;
    }
}
