package com.applidium.graphqlientdemo.data.net.retrofit.model;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface RestItem {
    String id();
    String name();
    int duration();
}
