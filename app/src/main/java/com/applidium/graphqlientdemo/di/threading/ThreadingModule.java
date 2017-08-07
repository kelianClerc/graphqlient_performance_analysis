package com.applidium.graphqlientdemo.di.threading;

import com.applidium.graphqlientdemo.utils.threading.JobExecutor;
import com.applidium.graphqlientdemo.utils.threading.PostExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.ThreadExecutor;
import com.applidium.graphqlientdemo.utils.threading.UiThread;

import dagger.Module;
import dagger.Provides;

@Module
public class ThreadingModule {

    @Provides
    ThreadExecutor provideExecutor(JobExecutor instance) {
        return instance;
    }

    @Provides
    PostExecutionThread providePostThread(UiThread instance) {
        return instance;
    }
}
