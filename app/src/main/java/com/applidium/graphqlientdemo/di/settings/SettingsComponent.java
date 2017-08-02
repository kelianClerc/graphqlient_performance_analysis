package com.applidium.graphqlientdemo.di.settings;

import com.applidium.graphqlientdemo.app.settings.ui.activity.SettingsActivity;
import com.applidium.graphqlientdemo.di.PerActivity;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.ContextModule;

import dagger.Component;

@PerActivity
@Component(
    dependencies = ApplicationComponent.class,
    modules = {ContextModule.class, SettingsModule.class}
)
public interface SettingsComponent {
    void inject(SettingsActivity activity);
}
