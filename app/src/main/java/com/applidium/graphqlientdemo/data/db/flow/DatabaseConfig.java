package com.applidium.graphqlientdemo.data.db.flow;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = DatabaseConfig.NAME, version = DatabaseConfig.VERSION)
public class DatabaseConfig {
    public static final String NAME = "Graphqldemo";
    public static final int VERSION = 1;
}
