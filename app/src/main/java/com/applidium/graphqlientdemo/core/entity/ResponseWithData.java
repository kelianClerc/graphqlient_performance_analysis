package com.applidium.graphqlientdemo.core.entity;

import com.applidium.graphqlientdemo.utils.logging.DataAnalyzer;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ResponseWithData<T> {
    DataAnalyzer logData();
    T data();
}
