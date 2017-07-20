package com.applidium.graphqlientdemo.di.example;

import com.applidium.graphqlientdemo.app.example.ui.activity.ExampleActivity;
import com.applidium.graphqlientdemo.di.PerActivity;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(
    modules = {ExampleModule.class},
    dependencies = ApplicationComponent.class
)
public interface ExampleComponent {
    void inject(ExampleActivity activity);
}
