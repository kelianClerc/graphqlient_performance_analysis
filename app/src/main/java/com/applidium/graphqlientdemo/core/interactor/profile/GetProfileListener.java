package com.applidium.graphqlientdemo.core.interactor.profile;

import com.applidium.graphqlientdemo.app.profile.model.ProfileViewModel;

public interface GetProfileListener {
    void onProfileSuccess(ProfileResponse response);
    void onProfileError(String errorMessage);
}
