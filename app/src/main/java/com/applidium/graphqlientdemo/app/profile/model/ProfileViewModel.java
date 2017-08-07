package com.applidium.graphqlientdemo.app.profile.model;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ProfileViewModel {
    String id();
    String name();
    String firstName();
    String age();
}
