package com.applidium.graphqlientdemo.app.users.presenter;

import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.users.model.UserMapper;
import com.applidium.graphqlientdemo.app.users.model.UserViewModel;
import com.applidium.graphqlientdemo.app.users.navigator.UsersNavigator;
import com.applidium.graphqlientdemo.app.users.ui.UsersViewContract;
import com.applidium.graphqlientdemo.core.interactor.users.GetUsersInteractor;
import com.applidium.graphqlientdemo.core.interactor.users.GetUsersListener;
import com.applidium.graphqlientdemo.core.interactor.users.UserResponse;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import java.util.List;

import javax.inject.Inject;

public class UsersPresenter extends Presenter<UsersViewContract> implements GetUsersListener {
    private final UserMapper userMapper;
    private final GetUsersInteractor restInteractor;
    private final UsersNavigator navigator;

    @Inject UsersPresenter(
        UsersViewContract view, UserMapper userMapper, GetUsersInteractor
        restInteractor, UsersNavigator navigator) {
        super(view);
        this.userMapper = userMapper;
        this.restInteractor = restInteractor;
        this.navigator = navigator;
    }

    @Override
    public void start() {
        restInteractor.execute(this);
    }

    @Override
    public void stop() {

    }

    public void onUser(UserViewModel user) {
        navigator.navigateToUserProfile(user.id());
    }

    @Override @Trace @Deprecated
    public void onUsersSuccess(List<UserResponse> response) {
        List<UserViewModel> users = userMapper.mapList(response);
        view.showUsers(users);
    }

    @Override @Trace @Deprecated
    public void onUsersError(String errorMessage) {
        // TODO (kelianclerc) 21/7/17
    }
}
