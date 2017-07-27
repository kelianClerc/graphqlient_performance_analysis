package com.applidium.graphqlientdemo.core.entity;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface User {
    String id();
    String name();
    String firstName();
    int age();
    int numberOfArticles();
}
