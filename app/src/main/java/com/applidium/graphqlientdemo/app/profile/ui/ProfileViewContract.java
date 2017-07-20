package com.applidium.graphqlientdemo.app.profile.ui;

import com.applidium.graphqlientdemo.app.common.ViewContract;
import com.applidium.graphqlientdemo.app.profile.model.ProfileViewModel;

public interface ProfileViewContract extends ViewContract {
    void showError();
    void showData(ProfileViewModel model);
}
