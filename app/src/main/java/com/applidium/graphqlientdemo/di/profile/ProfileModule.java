package com.applidium.graphqlientdemo.di.profile;
import com.applidium.graphqlientdemo.app.profile.ui.ProfileViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {
    private final ProfileViewContract viewContract;

    public ProfileModule(ProfileViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Provides
    ProfileViewContract viewContract() {
        return viewContract;
    }
}
