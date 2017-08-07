package com.applidium.graphqlientdemo.core.interactor.actions;

import com.applidium.graphqlientdemo.core.entity.Item;

import java.util.List;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ActionDetailResponse {
    String title();
    String id();
    List<Item> items();
}
