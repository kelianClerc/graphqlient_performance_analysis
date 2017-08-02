package com.applidium.graphqlientdemo.core.interactor.profile;

import com.applidium.graphqlientdemo.core.boundary.SourceRepository;
import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.data.LogRepository;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;
import javax.inject.Named;

public class GetProfileInteractor {
    private final UserRepository restRepository;
    private final UserRepository graphqlRepository;
    private final SourceRepository sourceRepository;
    private String userId;
    private String activityName;
    private GetProfileListener listener;
    private final LogRepository logRepository;

    @Inject
    GetProfileInteractor(
        @Named("rest") UserRepository restRepository,
        @Named("graphql") UserRepository graphqlRepository,
        SourceRepository sourceRepository,
        LogRepository logRepository
    ) {
        this.restRepository = restRepository;
        this.graphqlRepository = graphqlRepository;
        this.sourceRepository = sourceRepository;
        this.logRepository = logRepository;
    }

    @Trace
    @RunOnExecutionThread
    public void execute(String userId, String activityName, GetProfileListener listener) {
        this.userId = userId;
        this.activityName = activityName;
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
        if (sourceRepository.getSelectedSource()) {
            ResponseWithData<User> profile = restRepository.getProfile(userId, activityName);
            logRepository.writeLog(profile.logData());
            ProfileResponse response = makeResponse(profile.data());
            handleSuccess(response);
        }
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
