package com.applidium.graphqlientdemo.app.profile.ui;

import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;
import com.applidium.graphqlientdemo.app.common.ViewContract;
import com.applidium.graphqlientdemo.app.profile.model.ProfileViewModel;

import java.util.List;

public interface ProfileViewContract extends ViewContract {
    void showError();
    void showData(List<ActionViewModel> model);

    void showProfile(ProfileViewModel viewModel);
}
