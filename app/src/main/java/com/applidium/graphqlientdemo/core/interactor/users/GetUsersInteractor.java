package com.applidium.graphqlientdemo.core.interactor.users;

import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.data.LogRepository;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GetUsersInteractor {
    private final UserRepository repository;
    private String activityName;
    private GetUsersListener listener;
    private final LogRepository logRepository;

    @Inject
    GetUsersInteractor(UserRepository repository, LogRepository logRepository) {
        this.repository = repository;
        this.logRepository = logRepository;
    }

    @Trace
    @RunOnExecutionThread
    public void execute(String activityName, GetUsersListener listener) {
        this.activityName = activityName;
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
        ResponseWithData<List<User>> account = repository.getUsers(activityName);
        logRepository.writeLog(account.logData());
        List<UserResponse> response = makeResponse(account.data());
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
