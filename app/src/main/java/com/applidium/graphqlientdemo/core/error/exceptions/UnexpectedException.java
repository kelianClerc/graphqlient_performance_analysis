package com.applidium.graphqlientdemo.core.error.exceptions;

import com.applidium.graphqlientdemo.core.error.GraphqldemoException;
import com.applidium.graphqlientdemo.core.error.Errors;

public class UnexpectedException extends GraphqldemoException {
    @Override public int getId() {
        return Errors.GENERIC;
    }
}
