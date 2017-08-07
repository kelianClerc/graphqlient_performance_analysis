package com.applidium.graphqlientdemo.core.entity;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Item {
    String id();
    String name();
    String duration();
    boolean isDone();
}
