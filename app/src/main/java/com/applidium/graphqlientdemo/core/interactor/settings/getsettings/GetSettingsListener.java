package com.applidium.graphqlientdemo.core.interactor.settings.getsettings;

public interface GetSettingsListener {
    void onGetSettingsSuccess(boolean isRestChecked);
    void onGetSettingsError(String errorMessage);
}
