package com.applidium.graphqlientdemo.di.common;

import android.content.Context;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.boundary.SourceRepository;
import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.data.LogRepository;
import com.applidium.graphqlientdemo.data.LogRepositoryImpl;
import com.applidium.graphqlientdemo.data.net.ServiceSourceRepository;
import com.applidium.graphqlientdemo.data.net.retrofit.ServiceActionRepository;
import com.applidium.graphqlientdemo.data.net.retrofit.ServiceUserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    private final Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    UserRepository provideExampleRepository(
        ServiceUserRepository exampleRepository
    ) {
        return exampleRepository;
    }

    @Provides @Singleton
    ActionRepository provideActionsRepository(
        ServiceActionRepository exampleRepository
    ) {
        return exampleRepository;
    }


    @Provides @Singleton
    SourceRepository provideSourcesRepository(
        ServiceSourceRepository exampleRepository
    ) {
        return exampleRepository;
    }


    @Provides
    Context context() {
        return context;
    }

    @Provides @Singleton
    LogRepository provideLogRepository(LogRepositoryImpl instance) {
        return instance;
    }
}
