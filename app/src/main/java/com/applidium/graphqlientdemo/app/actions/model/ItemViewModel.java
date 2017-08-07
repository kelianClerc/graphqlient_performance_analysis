package com.applidium.graphqlientdemo.app.actions.model;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ItemViewModel {
    String id();
    String name();
    String duration();
    boolean isDone();
}
