package com.applidium.graphqlientdemo.data;

import com.applidium.graphqlientdemo.utils.logging.DataAnalyzer;

public interface LogRepository {
    public void writeLog(DataAnalyzer data);
}
