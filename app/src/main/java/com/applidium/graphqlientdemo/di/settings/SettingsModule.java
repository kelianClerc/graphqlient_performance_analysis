package com.applidium.graphqlientdemo.di.settings;

import com.applidium.graphqlientdemo.app.settings.ui.SettingsViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {
    private final SettingsViewContract viewContract;

    public SettingsModule(SettingsViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Provides
    SettingsViewContract viewContract() {
        return viewContract;
    }
}
