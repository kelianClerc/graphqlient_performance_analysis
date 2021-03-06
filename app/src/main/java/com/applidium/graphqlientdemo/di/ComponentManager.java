package com.applidium.graphqlientdemo.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.applidium.graphqlientdemo.app.actions.ui.ActionDetailViewContract;
import com.applidium.graphqlientdemo.app.actions.ui.ActionViewContract;
import com.applidium.graphqlientdemo.app.main.ui.MainViewContract;
import com.applidium.graphqlientdemo.app.profile.ui.ProfileViewContract;
import com.applidium.graphqlientdemo.app.settings.ui.SettingsViewContract;
import com.applidium.graphqlientdemo.app.users.ui.UsersViewContract;
import com.applidium.graphqlientdemo.di.actions.ActionDetailComponent;
import com.applidium.graphqlientdemo.di.actions.ActionDetailModule;
import com.applidium.graphqlientdemo.di.actions.ActionsComponent;
import com.applidium.graphqlientdemo.di.actions.ActionsModule;
import com.applidium.graphqlientdemo.di.actions.DaggerActionDetailComponent;
import com.applidium.graphqlientdemo.di.actions.DaggerActionsComponent;
import com.applidium.graphqlientdemo.di.common.ApplicationComponent;
import com.applidium.graphqlientdemo.di.common.ContextModule;
import com.applidium.graphqlientdemo.di.common.DaggerApplicationComponent;
import com.applidium.graphqlientdemo.di.common.FragmentManagerModule;
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
import com.applidium.graphqlientdemo.di.profile.DaggerProfileComponent;
import com.applidium.graphqlientdemo.di.profile.ProfileComponent;
import com.applidium.graphqlientdemo.di.profile.ProfileModule;
import com.applidium.graphqlientdemo.di.settings.DaggerSettingsComponent;
import com.applidium.graphqlientdemo.di.settings.SettingsComponent;
import com.applidium.graphqlientdemo.di.settings.SettingsModule;
import com.applidium.graphqlientdemo.di.threading.ThreadingComponent;
import com.applidium.graphqlientdemo.di.threading.ThreadingModule;
import com.applidium.graphqlientdemo.di.trace.TracerModule;
import com.applidium.graphqlientdemo.di.users.DaggerUsersComponent;
import com.applidium.graphqlientdemo.di.users.UsersComponent;
import com.applidium.graphqlientdemo.di.users.UsersModule;

import java.io.File;

public class ComponentManager {

    private static ApplicationComponent applicationComponent;
    private static LoggingComponent loggingComponent;
    private static ThreadingComponent threadingComponent;
    private static CrashesComponent crashesComponent;

    public static void init(SharedPreferences preferences, File cacheDirectory, Context applicationContext) {
        LoggingModule loggingModule = new LoggingModule();
        PreferencesModule preferencesModule = new PreferencesModule(preferences);
        ServiceModule serviceModule = new ServiceModule(cacheDirectory);
        TracerModule tracerModule = new TracerModule();
        RepositoryModule repositoryModule = new RepositoryModule(applicationContext);
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

    public static MainComponent getMainComponent(MainViewContract viewContract, Context context, FragmentManager manager) {
        FragmentManagerModule module = new FragmentManagerModule(manager);
        ContextModule contextModule = new ContextModule(context);
        return DaggerMainComponent
            .builder()
            .applicationComponent(getApplicationComponent())
            .mainModule(new MainModule(viewContract))
            .fragmentManagerModule(module)
            .contextModule(contextModule)
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

    public static ActionsComponent getActionsComponent(
        Context context,
        ActionViewContract viewContract,
        FragmentManager manager
    ) {
        return DaggerActionsComponent
            .builder()
            .applicationComponent(getApplicationComponent())
            .actionsModule(new ActionsModule(viewContract))
            .contextModule(new ContextModule(context))
            .fragmentManagerModule(new FragmentManagerModule(manager))
            .build();
    }

    public static UsersComponent getUsersComponent(
        Context context,
        UsersViewContract viewContract,
        FragmentManager manager
    ) {
        return DaggerUsersComponent
            .builder()
            .applicationComponent(getApplicationComponent())
            .usersModule(new UsersModule(viewContract))
            .contextModule(new ContextModule(context))
            .fragmentManagerModule(new FragmentManagerModule(manager))
            .build();
    }

    public static ProfileComponent getProfileComponent(
        ProfileViewContract viewContract,
        Context context
    ) {
        return DaggerProfileComponent
            .builder()
            .applicationComponent(getApplicationComponent())
            .profileModule(new ProfileModule(viewContract))
            .contextModule(new ContextModule(context))
            .build();
    }

    public static ActionDetailComponent getActionDetailComponent(
        ActionDetailViewContract viewContract, Context context
    ) {
        return DaggerActionDetailComponent
            .builder()
            .applicationComponent(getApplicationComponent())
            .actionDetailModule(new ActionDetailModule(viewContract))
            .contextModule(new ContextModule(context))
            .build();
    }

    public static SettingsComponent getSettingsComponent(
        SettingsViewContract viewContract, Context context
    ) {
        return DaggerSettingsComponent
            .builder()
            .applicationComponent(getApplicationComponent())
            .settingsModule(new SettingsModule(viewContract))
            .contextModule(new ContextModule(context))
            .build();
    }
}
