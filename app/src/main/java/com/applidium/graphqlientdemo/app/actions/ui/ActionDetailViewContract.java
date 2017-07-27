package com.applidium.graphqlientdemo.app.actions.ui;

import com.applidium.graphqlientdemo.app.actions.model.ActionDetailViewModel;
import com.applidium.graphqlientdemo.app.common.ViewContract;

public interface ActionDetailViewContract extends ViewContract {
    void showError(String errorMessage);
    void showDetail(ActionDetailViewModel viewModel);
}
