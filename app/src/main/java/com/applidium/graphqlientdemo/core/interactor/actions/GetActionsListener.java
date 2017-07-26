package com.applidium.graphqlientdemo.core.interactor.actions;

import java.util.List;

public interface GetActionsListener {
    void onActionsSuccess(List<ActionResponse> response);
    void onActionsError(String message);
}
