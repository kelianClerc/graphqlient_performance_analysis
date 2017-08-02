package com.applidium.graphqlientdemo.di.common;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.boundary.SourceRepository;
import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.data.LogRepository;
import com.applidium.graphqlientdemo.di.crashes.CrashesComponent;
import com.applidium.graphqlientdemo.di.crashes.CrashesModule;
import com.applidium.graphqlientdemo.di.logging.LoggingComponent;
import com.applidium.graphqlientdemo.di.logging.LoggingModule;
import com.applidium.graphqlientdemo.di.threading.ThreadingComponent;
import com.applidium.graphqlientdemo.di.threading.ThreadingModule;
import com.applidium.graphqlientdemo.di.trace.TracerModule;
import com.applidium.graphqlientdemo.utils.logging.Logger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    LoggingModule.class,
    PreferencesModule.class,
    ServiceModule.class,
    TracerModule.class,
    RepositoryModule.class
})
public interface ApplicationComponent {
    Logger logger();
    UserRepository exampleRepository();
    ActionRepository actionsRepository();
    SourceRepository sourceRepository();
    LogRepository logRepository();

    LoggingComponent.Builder loggingComponentBuilder();
    CrashesComponent plus(CrashesModule module);
    ThreadingComponent plus(ThreadingModule module);
}
