package com.applidium.graphqlientdemo.di.users;

import com.applidium.graphqlientdemo.app.users.ui.fragment.UsersFragment;
import com.applidium.graphqlientdemo.di.PerFragment;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.ContextModule;
import com.applidium.graphqlientdemo.di.common.FragmentManagerModule;

import dagger.Component;

@PerFragment
@Component(
    dependencies = ApplicationComponent.class,
    modules = {ContextModule.class, FragmentManagerModule.class, UsersModule.class}
)
public interface UsersComponent {
    void inject(UsersFragment fragment);
}
