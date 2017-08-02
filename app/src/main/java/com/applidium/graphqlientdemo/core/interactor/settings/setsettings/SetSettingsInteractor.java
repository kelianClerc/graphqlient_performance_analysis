package com.applidium.graphqlientdemo.core.interactor.settings.setsettings;

import com.applidium.graphqlientdemo.core.boundary.SourceRepository;
import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;

public class SetSettingsInteractor {
    private SetSettingsListener listener;
    private final SourceRepository repository;

    @Inject SetSettingsInteractor(SourceRepository repository) {
        this.repository = repository;
    }

    @Trace
    public void execute(boolean isRestChecked, SetSettingsListener listener) {
        this.listener = listener;
        tryToSetSettings(isRestChecked);
    }

    @RunOnExecutionThread
    private void tryToSetSettings(boolean isRestChecked) {
        try {
            repository.selectSource(isRestChecked);
            handleSuccess();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    @RunOnPostExecutionThread
    private void handleError(String errorMessage) {
        if (listener != null) {
            listener.onSetSettingsError(errorMessage);
        }
    }

    @RunOnPostExecutionThread
    private void handleSuccess() {
        if (listener != null) {
            listener.onSetSettingsSuccess();
        }
    }
}
