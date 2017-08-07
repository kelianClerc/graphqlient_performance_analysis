package com.applidium.graphqlientdemo.core.interactor.settings.setsettings;

public interface SetSettingsListener {
    void onSetSettingsSuccess();
    void onSetSettingsError(String errorMessage);
}
