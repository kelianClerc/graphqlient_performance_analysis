package com.applidium.graphqlientdemo.app.example.ui;

import android.support.annotation.StringRes;

import com.applidium.graphqlientdemo.app.common.ViewContract;

public interface ExampleViewContract extends ViewContract {
    void showMessage(@StringRes int message);
}
