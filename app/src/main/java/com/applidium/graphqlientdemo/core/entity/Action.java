package com.applidium.graphqlientdemo.core.entity;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Action {
    String title();
    String id();
    int numberOfSteps();
    String lastItem();
    int lastDuration();
    boolean isDone();
}
