package com.applidium.graphqlientdemo.core.error;

public abstract class GraphqldemoException extends Exception {
    public GraphqldemoException() {
    }

    public GraphqldemoException(String message) {
        super(message);
    }

    public abstract int getId();
}
