package com.applidium.graphqlientdemo.app.profile.presenter;

import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.profile.ui.ProfileViewContract;
import com.applidium.graphqlientdemo.core.interactor.actions.ActionResponse;
import com.applidium.graphqlientdemo.core.interactor.actions.GetActionsInteractor;
import com.applidium.graphqlientdemo.core.interactor.actions.GetActionsListener;
import com.applidium.graphqlientdemo.core.interactor.profile.GetProfileInteractor;
import com.applidium.graphqlientdemo.core.interactor.profile.GetProfileListener;
import com.applidium.graphqlientdemo.core.interactor.profile.ProfileResponse;

import java.util.List;

import javax.inject.Inject;

public class ProfilePresenter extends Presenter<ProfileViewContract> implements GetProfileListener, GetActionsListener {

    private final GetProfileInteractor interactor;
    private final GetActionsInteractor actionsInteractor;
    private String userId;

    @Inject ProfilePresenter(
        ProfileViewContract view,
        GetProfileInteractor interactor,
        GetActionsInteractor actionsInteractor
    ) {
        super(view);
        this.interactor = interactor;
        this.actionsInteractor = actionsInteractor;
    }

    public void onStart(String userId) {

        this.userId = userId;
    }

    @Override
    public void start() {
        interactor.execute(userId, this);
        actionsInteractor.execute(userId, this);
    }

    @Override
    public void stop() {

    }

    @Override
    public void onProfileSuccess(ProfileResponse response) {
        // TODO (kelianclerc) 21/7/17
    }

    @Override
    public void onProfileError(String errorMessage) {
        // TODO (kelianclerc) 21/7/17
    }

    @Override
    public void onActionsSuccess(List<ActionResponse> response) {
        // TODO (kelianclerc) 21/7/17
    }

    @Override
    public void onActionsError(String message) {
        // TODO (kelianclerc) 21/7/17
    }
}
