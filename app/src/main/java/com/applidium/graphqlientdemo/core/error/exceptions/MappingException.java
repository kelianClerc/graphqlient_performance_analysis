package com.applidium.graphqlientdemo.core.error.exceptions;

import com.applidium.graphqlientdemo.core.error.GraphqldemoException;
import com.applidium.graphqlientdemo.core.error.Errors;

public class MappingException extends GraphqldemoException {

    public MappingException() {
        /* no-op */
    }

    public MappingException(String message) {
        super(message);
    }

    @Override public int getId() {
        return Errors.MAPPING;
    }
}
