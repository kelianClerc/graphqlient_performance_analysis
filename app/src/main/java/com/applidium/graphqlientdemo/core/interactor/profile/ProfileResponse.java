package com.applidium.graphqlientdemo.core.interactor.profile;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ProfileResponse {
    String id();
    String name();
    String firstname();
    int age();
}
