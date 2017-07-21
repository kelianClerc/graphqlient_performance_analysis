package com.applidium.graphqlientdemo.core.interactor.users;

import java.util.List;

public interface GetUsersListener {
    void onUsersSuccess(List<UserResponse> response);
    void onUsersError(String errorMessage);
}
