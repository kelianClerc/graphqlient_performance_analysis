package com.applidium.graphqlientdemo.core.error.exceptions;

import android.support.annotation.Nullable;

import com.applidium.graphqlientdemo.core.error.Errors;
import com.applidium.graphqlientdemo.core.error.GraphqldemoException;

public class ServerClientException extends GraphqldemoException {

    private final String message;

    public ServerClientException(@Nullable String message) {
        this.message = message;
    }

    public String getServerMessage() {
        return message;
    }

    @Override public int getId() {
        return Errors.SERVER_CLIENT;
    }
}
