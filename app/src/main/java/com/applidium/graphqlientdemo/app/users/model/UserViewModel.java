package com.applidium.graphqlientdemo.app.users.model;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface UserViewModel {
    String id();
    String name();
    String age();
    String numberOfActions();
}
