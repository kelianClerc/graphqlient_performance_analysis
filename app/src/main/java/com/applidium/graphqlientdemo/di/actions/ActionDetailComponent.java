package com.applidium.graphqlientdemo.di.actions;

import com.applidium.graphqlientdemo.app.actions.ui.activity.ActionDetailActivity;
import com.applidium.graphqlientdemo.di.PerActivity;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.ContextModule;

import dagger.Component;

@PerActivity
@Component(
    dependencies = ApplicationComponent.class,
    modules = {ContextModule.class, ActionDetailModule.class}
)
public interface ActionDetailComponent {
    void inject(ActionDetailActivity fragment);
}
