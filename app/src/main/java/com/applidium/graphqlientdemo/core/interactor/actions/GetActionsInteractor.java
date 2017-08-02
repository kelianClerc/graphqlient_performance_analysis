package com.applidium.graphqlientdemo.core.interactor.actions;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.boundary.SourceRepository;
import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.data.LogRepository;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class GetActionsInteractor {
    private final ActionRepository restRepository;
    private final ActionRepository graphqlRepository;
    private final SourceRepository sourceRepository;
    private final LogRepository logRepository;
    private String userId;
    private String activityName;
    private GetActionsListener listener;

    @Inject
    GetActionsInteractor(
        @Named("rest") ActionRepository restRepository,
        @Named("graphql") ActionRepository graphqlRepository,
        SourceRepository sourceRepository,
        LogRepository logRepository
    ) {
        this.restRepository = restRepository;
        this.graphqlRepository = graphqlRepository;
        this.sourceRepository = sourceRepository;
        this.logRepository = logRepository;
    }

    @Trace @RunOnExecutionThread
    public void execute(String userId, String activityName, GetActionsListener listener) {
        this.userId = userId;
        this.activityName = activityName;
        this.listener = listener;
        tryToGetActions();
    }

    private void tryToGetActions() {
        try {
            getActions();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void getActions() throws Exception {
        ResponseWithData<List<Action>> profile = null;
        if (sourceRepository.getSelectedSource()) {
            profile = restRepository.getActions(userId, activityName);
        } else {
            profile = graphqlRepository.getActions(userId, activityName);
        }
        logRepository.writeLog(profile.logData());
        List<ActionResponse> response = makeResponse(profile.data());
        handleSuccess(response);
    }

    private List<ActionResponse> makeResponse(List<Action> actions) {
        List<ActionResponse> result = new ArrayList<>();
        for (Action action : actions) {
            ActionResponse response = new ActionResponseBuilder()
                .id(action.id())
                .title(action.title())
                .numberOfSteps(action.numberOfSteps())
                .lastActionName(action.lastItem())
                .isDone(action.isDone())
                .duration(action.lastDuration())
                .build();
            result.add(response);
        }
        return result;
    }

    @RunOnPostExecutionThread
    private void handleSuccess(List<ActionResponse> response) {
        if (listener != null) {
            listener.onActionsSuccess(response);
        }
    }

    @RunOnPostExecutionThread
    private void handleError(String errorMessage) {
        if (listener != null) {
            listener.onActionsError(errorMessage);
        }
    }
    @Trace
    public void done() {
        listener = null;
    }
}
