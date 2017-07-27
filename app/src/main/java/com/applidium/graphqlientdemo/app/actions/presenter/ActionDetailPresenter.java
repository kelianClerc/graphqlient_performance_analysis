package com.applidium.graphqlientdemo.app.actions.presenter;

import com.applidium.graphqlientdemo.app.actions.model.ActionDetailViewModel;
import com.applidium.graphqlientdemo.app.actions.model.ActionDetailViewModelMapper;
import com.applidium.graphqlientdemo.app.actions.ui.ActionDetailViewContract;
import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.core.interactor.actions.ActionDetailResponse;
import com.applidium.graphqlientdemo.core.interactor.actions.GetActionDetailInteractor;
import com.applidium.graphqlientdemo.core.interactor.actions.GetActionDetailListener;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;

public class ActionDetailPresenter extends Presenter<ActionDetailViewContract> implements GetActionDetailListener {
    private String actionId;
    private final GetActionDetailInteractor interactor;
    private final ActionDetailViewModelMapper actionDetailMapper;

    @Inject ActionDetailPresenter(
        ActionDetailViewContract view, GetActionDetailInteractor
        interactor, ActionDetailViewModelMapper actionDetailMapper) {
        super(view);
        this.interactor = interactor;
        this.actionDetailMapper = actionDetailMapper;
    }

    @Override
    public void start() {
        interactor.execute(actionId, this);
    }

    @Override @Trace
    public void stop() {

    }

    @Trace
    public void onStart(String actionId) {
        this.actionId = actionId;
        start();
    }

    @Override @Trace @Deprecated
    public void onActionDetailSuccess(ActionDetailResponse response) {
        ActionDetailViewModel viewModel = actionDetailMapper.map(response);
        view.showDetail(viewModel);
    }

    @Override @Trace @Deprecated
    public void onActionDetailError(String errorMessage) {
        view.showError(errorMessage);
    }
}
