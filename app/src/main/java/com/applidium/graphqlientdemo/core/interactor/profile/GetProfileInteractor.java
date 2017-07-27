package com.applidium.graphqlientdemo.core.interactor.profile;

import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;

public class GetProfileInteractor {
    private final UserRepository repository;
    private String userId;
    private GetProfileListener listener;

    @Inject
    GetProfileInteractor(UserRepository repository) {
        this.repository = repository;
    }

    @Trace
    @RunOnExecutionThread
    public void execute(String userId, GetProfileListener listener) {
        this.userId = userId;
        this.listener = listener;
        tryToGetProfile();
    }

    private void tryToGetProfile() {
        try {
            getProfile();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void getProfile() throws Exception {
        User profile = repository.getProfile(userId);
        ProfileResponse response = makeResponse(profile);
        handleSuccess(response);
    }

    private ProfileResponse makeResponse(User users) {
        ProfileResponse result = new ProfileResponseBuilder()
            .id(users.id())
            .name(users.name())
            .firstname(users.firstName())
            .age(users.age())
            .build();
        return result;
    }

    @RunOnPostExecutionThread
    private void handleSuccess(ProfileResponse response) {
        if (listener != null) {
            listener.onProfileSuccess(response);
        }
    }

    @RunOnPostExecutionThread
    private void handleError(String errorMessage) {
        if (listener != null) {
            listener.onProfileError(errorMessage);
        }
    }
    @Trace
    public void done() {
        listener = null;
    }
}
