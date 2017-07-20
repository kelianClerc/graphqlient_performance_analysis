package com.applidium.graphqlientdemo.di.common;

import com.applidium.graphqlientdemo.core.boundary.ExampleRepository;
import com.applidium.graphqlientdemo.data.ExampleRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides @Singleton ExampleRepository provideExampleRepository(
        ExampleRepositoryImpl exampleRepository
    ) {
        return exampleRepository;
    }
}
