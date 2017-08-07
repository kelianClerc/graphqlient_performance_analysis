package com.applidium.graphqlientdemo.di.profile;

import com.applidium.graphqlientdemo.app.profile.ui.activity.ProfileActivity;
import com.applidium.graphqlientdemo.di.PerActivity;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.ContextModule;

import dagger.Component;

@PerActivity
@Component(
    dependencies = ApplicationComponent.class,
    modules = {ContextModule.class, ProfileModule.class}
)
public interface ProfileComponent {
    void inject(ProfileActivity fragment);
}
