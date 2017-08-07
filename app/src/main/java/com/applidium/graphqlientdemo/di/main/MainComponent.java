package com.applidium.graphqlientdemo.di.main;

import com.applidium.graphqlientdemo.app.main.ui.activity.MainActivity;
import com.applidium.graphqlientdemo.di.PerActivity;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.ContextModule;
import com.applidium.graphqlientdemo.di.common.FragmentManagerModule;

import dagger.Component;

@PerActivity
@Component(
    modules = {FragmentManagerModule.class, ContextModule.class, MainModule.class},
    dependencies = ApplicationComponent.class
)
public interface MainComponent {
    void inject(MainActivity activity);
}
