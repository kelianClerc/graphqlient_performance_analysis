package com.applidium.graphqlientdemo.app;

import android.support.annotation.NonNull;

import com.applidium.graphqlientdemo.Settings;
import com.applidium.graphqlientdemo.di.ComponentManager;
import com.applidium.graphqlientdemo.util.stetho.SQLDumpPlugin;
import com.applidium.graphqlientdemo.utils.logging.Logger;
import com.applidium.graphqlientdemo.utils.logging.LoggerComponentCallbacks2;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.InspectorModulesProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

public class DebugGraphqldemoApplication extends GraphqldemoApplication {

    private static final boolean DEV_METRICS_ENABLED = Settings.performance.dev_metrics;
    private static final boolean SHOW_MEMORY_LEAKS = Settings.performance.show_memory_leaks;
    private static final boolean STETHO_ENABLED = Settings.performance.stetho;

    private LoggerComponentCallbacks2 loggerComponentCallback;

    @Override
    public void onCreate() {
        super.onCreate();
        setupComponentCallback();
        setupStetho();
        setupDevMetrics();
        setupLeakCanary();
        registerComponentCallbacks(loggerComponentCallback);
    }

    private void setupLeakCanary() {
        if (!SHOW_MEMORY_LEAKS) {
            return;
        }
        LeakCanary.install(this);
    }

    private void setupDevMetrics() {
        if (!DEV_METRICS_ENABLED) {
            return;
        }
        AndroidDevMetrics.initWith(this);
    }

    private void setupStetho() {
        if (!STETHO_ENABLED) {
            return;
        }
        DumperPluginsProvider pluginsProvider = getStethoPluginsProvider();
        InspectorModulesProvider modulesProvider = Stetho.defaultInspectorModulesProvider(this);
        Stetho.InitializerBuilder builder = Stetho.newInitializerBuilder(this);
        builder
            .enableDumpapp(pluginsProvider)
            .enableWebKitInspector(modulesProvider);
        Stetho.Initializer initializer = builder.build();
        Stetho.initialize(initializer);
    }

    @NonNull
    private DumperPluginsProvider getStethoPluginsProvider() {
        return new DumperPluginsProvider() {
            @Override
            public Iterable<DumperPlugin> get() {
                return getDumperPlugins();
            }
        };
    }

    @NonNull
    private Iterable<DumperPlugin> getDumperPlugins() {
        List<DumperPlugin> plugins = new ArrayList<>();
        plugins.add(new SQLDumpPlugin(this));
        DumperPluginsProvider defaultProvider = Stetho.defaultDumperPluginsProvider(this);
        for (DumperPlugin plugin : defaultProvider.get()) {
            plugins.add(plugin);
        }
        return plugins;
    }

    private void setupComponentCallback() {
        Logger logger = ComponentManager.getApplicationComponent().logger();
        loggerComponentCallback = new LoggerComponentCallbacks2(logger);
    }
}
