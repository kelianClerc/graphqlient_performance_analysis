package com.applidium.graphqlientdemo.app.settings.presenter;

import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.settings.ui.SettingsViewContract;
import com.applidium.graphqlientdemo.core.interactor.settings.getsettings.GetSettingsInteractor;
import com.applidium.graphqlientdemo.core.interactor.settings.getsettings.GetSettingsListener;
import com.applidium.graphqlientdemo.core.interactor.settings.setsettings.SetSettingsInteractor;
import com.applidium.graphqlientdemo.core.interactor.settings.setsettings.SetSettingsListener;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;

public class SettingsPresenter extends Presenter<SettingsViewContract> implements GetSettingsListener, SetSettingsListener {

    private final GetSettingsInteractor getInteractor;
    private final SetSettingsInteractor setInteractor;

    @Inject SettingsPresenter(
        SettingsViewContract view,
        GetSettingsInteractor getInteractor,
        SetSettingsInteractor setInteractor
    ) {
        super(view);
        this.getInteractor = getInteractor;
        this.setInteractor = setInteractor;
    }

    @Override @Trace
    public void start() {
        getInteractor.execute(this);
    }

    @Override @Trace
    public void stop() {

    }

    @Trace
    public void onSourceSet(boolean restChecked) {
        setInteractor.execute(restChecked, this);
    }

    @Override @Trace @Deprecated
    public void onGetSettingsSuccess(boolean isRestChecked) {
        view.setDefaultValue(isRestChecked);
    }

    @Override @Trace @Deprecated
    public void onGetSettingsError(String errorMessage) {
        view.showGetError(errorMessage);
    }

    @Override @Trace @Deprecated
    public void onSetSettingsSuccess() {
        view.showSetSuccess();
    }

    @Override @Trace @Deprecated
    public void onSetSettingsError(String errorMessage) {
        view.showSetError(errorMessage);
    }
}
