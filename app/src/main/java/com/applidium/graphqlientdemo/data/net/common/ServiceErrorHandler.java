package com.applidium.graphqlientdemo.data.net.common;

import com.applidium.graphqlientdemo.data.net.retrofit.model.RestError;

public interface ServiceErrorHandler {
    void handleClientError(int code, RestError error);
}
