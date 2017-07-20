package com.applidium.graphqlientdemo.di.example;

import com.applidium.graphqlientdemo.app.example.ui.ExampleViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ExampleModule {
    private final ExampleViewContract viewContract;

    public ExampleModule(ExampleViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Provides
    public ExampleViewContract provideViewContract() {
        return viewContract;
    }
}
