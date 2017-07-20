package com.applidium.graphqlientdemo.utils.mapper;

import com.applidium.graphqlientdemo.core.error.exceptions.MappingException;

public interface UnsafeMapper<U, T> {
    T map(U toMap) throws MappingException;
}
