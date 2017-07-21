package com.applidium.graphqlientdemo.di.actions;

import com.applidium.graphqlientdemo.app.actions.ui.fragment.ActionsFragment;
import com.applidium.graphqlientdemo.di.PerFragment;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.ContextModule;
import com.applidium.graphqlientdemo.di.common.FragmentManagerModule;

import dagger.Component;

@PerFragment
@Component(
    dependencies = ApplicationComponent.class,
    modules = {ContextModule.class, FragmentManagerModule.class, ActionsModule.class}
)
public interface ActionsComponent {
    void inject(ActionsFragment fragment);
}
