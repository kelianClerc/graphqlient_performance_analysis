package com.applidium.graphqlientdemo.di.main;

import com.applidium.graphqlientdemo.app.main.ui.MainViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private final MainViewContract viewContract;

    public MainModule(MainViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Provides
    public MainViewContract provideViewContract() {
        return viewContract;
    }
}
