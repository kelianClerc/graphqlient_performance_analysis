package com.applidium.graphqlientdemo.di.common;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.data.net.retrofit.ServiceActionRepository;
import com.applidium.graphqlientdemo.data.net.retrofit.ServiceUserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
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
}
