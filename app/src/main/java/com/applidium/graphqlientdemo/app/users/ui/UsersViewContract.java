package com.applidium.graphqlientdemo.app.users.ui;

import com.applidium.graphqlientdemo.app.common.ViewContract;
import com.applidium.graphqlientdemo.app.users.model.UserViewModel;

import java.util.List;

public interface UsersViewContract extends ViewContract {
    void showUsers(List<UserViewModel> viewModels);
}
