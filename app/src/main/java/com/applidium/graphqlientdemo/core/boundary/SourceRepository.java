package com.applidium.graphqlientdemo.core.boundary;

public interface SourceRepository {
    boolean getSelectedSource() throws Exception;
    void selectSource(boolean isRestSelected) throws Exception;
}
