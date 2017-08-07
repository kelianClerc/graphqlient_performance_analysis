package com.applidium.graphqlientdemo.utils.mapper;

public interface Mapper<U, T> {
    T map(U toMap);
}
