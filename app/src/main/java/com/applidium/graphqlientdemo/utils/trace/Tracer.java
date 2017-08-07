package com.applidium.graphqlientdemo.utils.trace;

public interface Tracer {
    void trace(Object target, String message, Object... parameterValues);
}
