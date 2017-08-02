package com.applidium.graphqlientdemo.core.interactor.settings.getsettings;

import com.applidium.graphqlientdemo.core.boundary.SourceRepository;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;

public class GetSettingsInteractor {
    private GetSettingsListener listener;
    private final SourceRepository repository;

    @Inject GetSettingsInteractor(SourceRepository repository) {
        this.repository = repository;
    }

    @Trace
    public void execute(GetSettingsListener listener) {
        this.listener = listener;
        tryToGetSettings();
    }

    @RunOnExecutionThread
    private void tryToGetSettings() {
        boolean isRestSelected = false;
        try {
            isRestSelected = repository.getSelectedSource();
            handleSuccess(isRestSelected);
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    @RunOnPostExecutionThread
    private void handleError(String errorMessage) {
        if (listener != null) {
            listener.onGetSettingsError(errorMessage);
        }
    }

    @RunOnPostExecutionThread
    private void handleSuccess(boolean isRestSelected) {
        if (listener != null) {
            listener.onGetSettingsSuccess(isRestSelected);
        }
    }
}
