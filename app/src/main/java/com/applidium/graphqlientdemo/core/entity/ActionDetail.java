package com.applidium.graphqlientdemo.core.entity;

import java.util.List;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ActionDetail {
    String id();
    String title();
    List<Item> items();
}
