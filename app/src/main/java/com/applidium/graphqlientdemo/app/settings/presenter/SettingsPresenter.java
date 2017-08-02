package com.applidium.graphqlientdemo.app.settings.presenter;

import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.settings.ui.SettingsViewContract;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import javax.inject.Inject;

public class SettingsPresenter extends Presenter<SettingsViewContract> {
    @Inject SettingsPresenter(SettingsViewContract view) {
        super(view);
    }

    @Override @Trace
    public void start() {

    }

    @Override @Trace
    public void stop() {

    }

    @Trace
    public void onSourceSet(boolean restChecked) {
        
    }
}
