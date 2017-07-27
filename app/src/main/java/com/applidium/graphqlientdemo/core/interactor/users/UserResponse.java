package com.applidium.graphqlientdemo.core.interactor.users;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface UserResponse {
    String name();
    String firstName();
    int age();
    String id();
    int numberOfActions();
}
