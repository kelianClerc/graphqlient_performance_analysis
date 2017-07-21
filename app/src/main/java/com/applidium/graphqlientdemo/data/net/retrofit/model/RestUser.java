package com.applidium.graphqlientdemo.data.net.retrofit.model;

import java.util.List;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface RestUser {
    String id();
    String name();
    String firstname();
    int age();
    List<RestActionId> actions();
}
