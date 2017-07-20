package com.applidium.graphqlientdemo.di;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.applidium.graphqlientdemo.app.main.ui.MainViewContract;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.DaggerApplicationComponent;
import com.applidium.graphqlientdemo.di.common.PreferencesModule;
import com.applidium.graphqlientdemo.di.common.RepositoryModule;
import com.applidium.graphqlientdemo.di.common.ServiceModule;
import com.applidium.graphqlientdemo.di.crashes.CrashesComponent;
import com.applidium.graphqlientdemo.di.crashes.CrashesModule;
import com.applidium.graphqlientdemo.di.logging.LoggingComponent;
import com.applidium.graphqlientdemo.di.logging.LoggingModule;
import com.applidium.graphqlientdemo.di.main.DaggerMainComponent;
import com.applidium.graphqlientdemo.di.main.MainComponent;
import com.applidium.graphqlientdemo.di.main.MainModule;
import com.applidium.graphqlientdemo.di.threading.ThreadingComponent;
import com.applidium.graphqlientdemo.di.threading.ThreadingModule;
import com.applidium.graphqlientdemo.di.trace.TracerModule;

import java.io.File;

public class ComponentManager {

    private static ApplicationComponent applicationComponent;
    private static LoggingComponent loggingComponent;
    private static ThreadingComponent threadingComponent;
    private static CrashesComponent crashesComponent;

    public static void init(SharedPreferences preferences, File cacheDirectory) {
        LoggingModule loggingModule = new LoggingModule();
        PreferencesModule preferencesModule = new PreferencesModule(preferences);
        ServiceModule serviceModule = new ServiceModule(cacheDirectory);
        TracerModule tracerModule = new TracerModule();
        RepositoryModule repositoryModule = new RepositoryModule();
        initApplicationComponent(
            loggingModule,
            preferencesModule,
            serviceModule,
            tracerModule,
            repositoryModule
        );
        initLoggingComponent();
        ThreadingModule threadingModule = new ThreadingModule();
        initThreadingComponent(threadingModule);
        CrashesModule crashesModule = new CrashesModule();
        initCrashesComponent(crashesModule);
    }

    public static ApplicationComponent getApplicationComponent() {
        return safeReturn(applicationComponent);
    }

    public static LoggingComponent getLoggingComponent() {
        return safeReturn(loggingComponent);
    }

    public static ThreadingComponent getThreadingComponent() {
        return safeReturn(threadingComponent);
    }

    public static CrashesComponent getCrashesComponent() {
        return safeReturn(crashesComponent);
    }

    public static MainComponent getMainComponent(MainViewContract viewContract) {
        return DaggerMainComponent
            .builder()
            .applicationComponent(getApplicationComponent())
            .mainModule(new MainModule(viewContract))
            .build();
    }

    private static void initApplicationComponent(
        LoggingModule loggingModule,
        PreferencesModule preferencesModule,
        ServiceModule serviceModule,
        TracerModule tracerModule,
        RepositoryModule repositoryModule
    ) {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .loggingModule(loggingModule)
            .tracerModule(tracerModule)
            .preferencesModule(preferencesModule)
            .serviceModule(serviceModule)
            .repositoryModule(repositoryModule)
            .build();
    }

    private static void initLoggingComponent() {
        loggingComponent = applicationComponent.loggingComponentBuilder().build();
    }

    private static void initThreadingComponent(ThreadingModule threadingModule) {
        threadingComponent = applicationComponent.plus(threadingModule);
    }

    private static void initCrashesComponent(CrashesModule crashesModule) {
        crashesComponent = applicationComponent.plus(crashesModule);
    }

    @NonNull
    private static <C> C safeReturn(C component) {
        if (component == null) {
            fail();
        }
        return component;
    }

    private static void fail() {
        String message = "ComponentManager.init() was not called on Application#onCreate()";
        throw new RuntimeException(message);
    }
}
