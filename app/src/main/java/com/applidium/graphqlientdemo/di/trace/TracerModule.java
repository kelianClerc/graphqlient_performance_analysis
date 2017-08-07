package com.applidium.graphqlientdemo.di.trace;

import android.support.annotation.NonNull;

import com.applidium.graphqlientdemo.Settings;
import com.applidium.graphqlientdemo.utils.crashes.HockeyAppCrashManagerListener;
import com.applidium.graphqlientdemo.utils.trace.LoggerTracer;
import com.applidium.graphqlientdemo.utils.trace.NoOpTracer;
import com.applidium.graphqlientdemo.utils.trace.Tracer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TracerModule {

    protected static final boolean TRACING = Settings.tracing.enabled;
    protected static final boolean LOGGING = Settings.logging.enabled;
    protected static final boolean CRASHES = Settings.crashes.enabled;

    @Provides @Singleton
    protected Tracer provideTracer(
        final HockeyAppCrashManagerListener hockeyAppTracer,
        NoOpTracer noOpTracer,
        final LoggerTracer loggerTracer
    ) {
        if (!TRACING) {
            return noOpTracer;
        }
        if (CRASHES && LOGGING) {
            return getWrapperTracer(hockeyAppTracer, loggerTracer);
        }
        if (CRASHES) {
            return hockeyAppTracer;
        }
        if (LOGGING) {
            return loggerTracer;
        }
        return noOpTracer;
    }

    @NonNull
    private Tracer getWrapperTracer(
        final HockeyAppCrashManagerListener hockeyAppTracer, final LoggerTracer loggerTracer
    ) {
        return new Tracer() {
            @Override
            public void trace(Object target, String message, Object[] parameterValues) {
                hockeyAppTracer.trace(target, message, parameterValues);
                loggerTracer.trace(target, message, parameterValues);
            }
        };
    }
}
