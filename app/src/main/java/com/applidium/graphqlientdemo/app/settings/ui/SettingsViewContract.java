package com.applidium.graphqlientdemo.app.settings.ui;

import com.applidium.graphqlientdemo.app.common.ViewContract;

public interface SettingsViewContract extends ViewContract {
    void showSetError(String errorMessage);
    void showSetSuccess();
    void showGetError(String errorMessage);
    void setDefaultValue(boolean isRestChecked);
}
