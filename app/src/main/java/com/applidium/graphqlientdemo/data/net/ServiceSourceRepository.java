package com.applidium.graphqlientdemo.data.net;

import android.content.SharedPreferences;

import com.applidium.graphqlientdemo.core.boundary.SourceRepository;

import javax.inject.Inject;

public class ServiceSourceRepository implements SourceRepository {

    public static final String SELECTED_SOURCE_KEY = "SELECTED_SOURCE_KEY";
    private final SharedPreferences prefs;

    @Inject ServiceSourceRepository(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public boolean getSelectedSource() throws Exception {
        return prefs.getBoolean(SELECTED_SOURCE_KEY, true);
    }

    @Override
    public void selectSource(boolean isRestSelected) throws Exception {
        prefs.edit().putBoolean(SELECTED_SOURCE_KEY, isRestSelected).apply();
    }
}
