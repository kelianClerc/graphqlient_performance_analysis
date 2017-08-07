package com.applidium.graphqlientdemo.utils.logging;

public interface DataAnalysisListener {
    void interceptSize(long length, long time, boolean isRequest, double id);
}
