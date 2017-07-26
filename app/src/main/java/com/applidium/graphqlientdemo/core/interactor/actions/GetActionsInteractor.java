package com.applidium.graphqlientdemo.core.interactor.actions;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GetActionsInteractor {
    private final ActionRepository repository;
    private String userId;
    private GetActionsListener listener;

    @Inject
    GetActionsInteractor(ActionRepository repository) {
        this.repository = repository;
    }

    public void execute(String userId, GetActionsListener listener) {
        this.userId = userId;
        this.listener = listener;
    }

    private void tryToGetActions() {
        try {
            getActions();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void getActions() throws Exception {
        List<Action> profile = repository.getActions(userId);
        List<ActionResponse> response = makeResponse(profile);
        handleSuccess(response);
    }

    private List<ActionResponse> makeResponse(List<Action> actions) {
        List<ActionResponse> result = new ArrayList<>();
        for (Action action : actions) {
            result.add(new ActionsResponseBuilder()
                .title(action.title())
                .numberOfSteps(action.numberOfSteps())
                .lastActionName(action.lastItem())
                .duration(action.lastDuration())
                .isDone(action.isDone())
                .build());
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
