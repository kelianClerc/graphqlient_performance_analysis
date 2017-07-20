package com.applidium.graphqlientdemo.app.example.presenter;

import android.support.annotation.StringRes;

import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.example.ui.ExampleViewContract;
import com.applidium.graphqlientdemo.core.interactor.ExampleInteractor;
import com.applidium.graphqlientdemo.core.interactor.ExampleListener;

import javax.inject.Inject;

public class ExamplePresenter extends Presenter<ExampleViewContract> implements ExampleListener {

    private boolean viewIsAlreadyLoadedWithData;
    private final ExampleInteractor interactor;

    @Inject ExamplePresenter(ExampleViewContract view, ExampleInteractor interactor) {
        super(view);
        this.interactor = interactor;
        viewIsAlreadyLoadedWithData = false;
    }

    @Override public void start() {
        if (viewIsAlreadyLoadedWithData) {
            return;
        }
        interactor.execute(this, null);
    }

    @Override public void stop() {
        interactor.removeListener(this);
    }

    @Override
    public void onResult(@StringRes int message) {
        viewIsAlreadyLoadedWithData = true;
        view.showMessage(message);
    }
}
