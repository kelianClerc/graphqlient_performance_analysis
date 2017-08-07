package com.applidium.graphqlientdemo.app.actions.model;

import java.util.List;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ActionDetailViewModel {
    String id();
    String title();
    List<ItemViewModel> items();
}
