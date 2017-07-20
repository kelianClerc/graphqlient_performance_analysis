package com.applidium.graphqlientdemo.app.actions.ui;

import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;
import com.applidium.graphqlientdemo.app.common.ViewContract;

import java.util.List;

public interface ActionViewContract extends ViewContract {
    void showActions(List<ActionViewModel> viewModels);
}
