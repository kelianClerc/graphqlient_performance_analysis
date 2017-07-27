package com.applidium.graphqlientdemo.core.interactor.actions;

import java.util.List;

public interface GetActionDetailListener {
    void onActionDetailSuccess(ActionDetailResponse response);
    void onActionDetailError(String errorMessage);
}
