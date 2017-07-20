package com.applidium.graphqlientdemo.di.threading;

import com.applidium.graphqlientdemo.utils.aspect.ThreadingAspect;

import dagger.Subcomponent;

@Subcomponent(modules = ThreadingModule.class)
public interface ThreadingComponent {
    void inject(ThreadingAspect aspect);
}
