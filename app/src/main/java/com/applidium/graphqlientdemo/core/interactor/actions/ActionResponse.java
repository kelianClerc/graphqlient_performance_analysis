package com.applidium.graphqlientdemo.core.interactor.actions;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ActionResponse {
    String title();
    int numberOfSteps();
    String lastActionName();
    boolean isDone();
    int duration();
}
