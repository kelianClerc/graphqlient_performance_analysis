package com.applidium.graphqlientdemo.data.net.retrofit.model;

import java.util.List;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface RestUsersContent {
    List<RestUser> users();
}
