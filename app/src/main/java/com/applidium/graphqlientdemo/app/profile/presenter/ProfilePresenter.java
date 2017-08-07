package com.applidium.graphqlientdemo.app.profile.presenter;

import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;
import com.applidium.graphqlientdemo.app.actions.model.ActionViewModelMapper;
import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.profile.model.ProfileViewModel;
import com.applidium.graphqlientdemo.app.profile.model.ProfileViewModelBuilder;
import com.applidium.graphqlientdemo.app.profile.navigator.ProfileNavigator;
import com.applidium.graphqlientdemo.app.profile.ui.ProfileViewContract;
import com.applidium.graphqlientdemo.core.interactor.actions.ActionResponse;
import com.applidium.graphqlientdemo.core.interactor.actions.GetActionsInteractor;
import com.applidium.graphqlientdemo.core.interactor.actions.GetActionsListener;
import com.applidium.graphqlientdemo.core.interactor.profile.GetProfileInteractor;
import com.applidium.graphqlientdemo.core.interactor.profile.GetProfileListener;
import com.applidium.graphqlientdemo.core.interactor.profile.ProfileResponse;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import java.util.List;

import javax.inject.Inject;

public class ProfilePresenter extends Presenter<ProfileViewContract> implements GetProfileListener, GetActionsListener {


    public static final String ACTIVITY_NAME = "ProfileActivity";
    private final GetProfileInteractor interactor;
    private final GetActionsInteractor actionsInteractor;
    private final ActionViewModelMapper actionMapper;
    private String userId;
    private final ProfileNavigator navigator;

    @Inject ProfilePresenter(
        ProfileViewContract view,
        GetProfileInteractor interactor,
        GetActionsInteractor actionsInteractor,
        ProfileNavigator navigator,
        ActionViewModelMapper actionMapper
    ) {
        super(view);
        this.interactor = interactor;
        this.actionsInteractor = actionsInteractor;
        this.actionMapper = actionMapper;
        this.navigator = navigator;
    }

    @Trace
    public void onStart(String userId) {
        this.userId = userId;
        start();
    }

    @Override @Trace
    public void start() {
        interactor.execute(userId, ACTIVITY_NAME, this);
        actionsInteractor.execute(userId, ACTIVITY_NAME, this);
    }

    @Override
    public void stop() {

    }

    @Override @Trace @Deprecated
    public void onProfileSuccess(ProfileResponse response) {
        ProfileViewModel viewModel = new ProfileViewModelBuilder()
            .id(response.id())
            .name(response.name())
            .firstName(response.firstname())
            .age(String.valueOf(response.age()))
            .build();

        view.showProfile(viewModel);
    }

    @Override @Trace @Deprecated
    public void onProfileError(String errorMessage) {
        // TODO (kelianclerc) 21/7/17
    }

    @Override @Trace @Deprecated
    public void onActionsSuccess(List<ActionResponse> response) {
        List<ActionViewModel> viewModels = actionMapper.mapList(response);
        view.showData(viewModels);
    }

    @Override @Trace @Deprecated
    public void onActionsError(String message) {
        // TODO (kelianclerc) 21/7/17
    }

    public void onAction(String id) {
        navigator.navigateToActionDetail(id);
    }
}
