package com.applidium.graphqlientdemo.core.interactor.actions;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.boundary.SourceRepository;
import com.applidium.graphqlientdemo.core.entity.ActionDetail;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.data.LogRepository;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;
import javax.inject.Named;

public class GetActionDetailInteractor {
    private final ActionRepository restRepository;
    private final ActionRepository graphqlRepository;
    private final SourceRepository sourceRepository;
    private String actionId;
    private String activityName;
    private GetActionDetailListener listener;
    private final LogRepository logRepository;

    @Inject
    GetActionDetailInteractor(
        @Named("rest") ActionRepository restRepository,
        @Named("graphql") ActionRepository graphqlRepository,
        SourceRepository sourceRepository,
        LogRepository logRepository) {

        this.restRepository = restRepository;
        this.graphqlRepository = graphqlRepository;
        this.sourceRepository = sourceRepository;
        this.logRepository = logRepository;
    }

    @Trace
    @RunOnExecutionThread
    public void execute(String actionId, String activityName, GetActionDetailListener listener) {
        this.actionId = actionId;
        this.activityName = activityName;
        this.listener = listener;
        tryToGetActionDetail();
    }

    private void tryToGetActionDetail() {
        try {

            getActions();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void getActions() throws Exception {
        ResponseWithData<ActionDetail> profile = null;
        if (sourceRepository.getSelectedSource()) {
            profile = restRepository.getActionDetail(actionId, activityName);
        } else {
            profile = graphqlRepository.getActionDetail(actionId, activityName);
        }
        logRepository.writeLog(profile.logData());
        ActionDetailResponse response = makeResponse(profile.data());
        handleSuccess(response);
    }

    private ActionDetailResponse makeResponse(ActionDetail actions) {
        return new ActionDetailResponseBuilder()
            .title(actions.title())
            .id(actions.id())
            .items(actions.items())
            .build();
    }

    @RunOnPostExecutionThread
    private void handleSuccess(ActionDetailResponse response) {
        if (listener != null) {
            listener.onActionDetailSuccess(response);
        }
    }

    @RunOnPostExecutionThread
    private void handleError(String errorMessage) {
        if (listener != null) {
            listener.onActionDetailError(errorMessage);
        }
    }
    @Trace
    public void done() {
        listener = null;
    }
}
