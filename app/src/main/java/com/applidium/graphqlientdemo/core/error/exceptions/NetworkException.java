package com.applidium.graphqlientdemo.core.error.exceptions;

import com.applidium.graphqlientdemo.core.error.GraphqldemoException;
import com.applidium.graphqlientdemo.core.error.Errors;

public class NetworkException extends GraphqldemoException {
    @Override public int getId() {
        return Errors.UNAVAILABLE_SERVICE;
    }
}
