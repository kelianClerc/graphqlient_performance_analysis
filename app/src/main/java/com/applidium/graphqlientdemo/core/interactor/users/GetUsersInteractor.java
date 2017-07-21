package com.applidium.graphqlientdemo.core.interactor.users;

import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import com.applidium.graphqlientdemo.core.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GetUsersInteractor {
    private final UserRepository repository;
    private GetUsersListener listener;

    @Inject
    GetUsersInteractor(UserRepository repository) {
        this.repository = repository;
    }

    @Trace
    @RunOnExecutionThread
    public void execute(GetUsersListener listener) {
        this.listener = listener;
        tryToGetUsers();
    }

    private void tryToGetUsers() {
        try {
            getUsers();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void getUsers() throws Exception {
        List<User> account = repository.getUsers();
        List<UserResponse> response = makeResponse(account);
        handleSuccess(response);
    }

    private List<UserResponse> makeResponse(List<User> users) {
        List<UserResponse> result = new ArrayList<>();
        for(User account : users) {
            result.add(new UserResponseBuilder()
                .id(account.id())
                .firstName(account.firstName())
                .name(account.name())
                .age(account.age())
                .numberOfActions(account.numberOfArticles())
                .build());
        }
        return result;
    }

    @RunOnPostExecutionThread
    private void handleSuccess(List<UserResponse> response) {
        if (listener != null) {
            listener.onUsersSuccess(response);
        }
    }

    @RunOnPostExecutionThread
    private void handleError(String errorMessage) {
        if (listener != null) {
            listener.onUsersError(errorMessage);
        }
    }
    @Trace
    public void done() {
        listener = null;
    }
}
