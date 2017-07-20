package com.applidium.graphqlientdemo.app.actions.model;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ActionViewModel {
    String articleName();
    String numberOfItems();
    String lastItemTitle();
    String lastItemDuration();
    boolean isDone();
}
