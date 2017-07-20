package com.applidium.graphqlientdemo.di.logging;

import com.applidium.graphqlientdemo.app.common.BaseActivity;
import com.applidium.graphqlientdemo.app.common.BaseDialog;
import com.applidium.graphqlientdemo.app.common.BaseDialogFragment;
import com.applidium.graphqlientdemo.app.common.BaseFragment;
import com.applidium.graphqlientdemo.app.common.BaseIntentService;
import com.applidium.graphqlientdemo.app.common.BaseService;
import com.applidium.graphqlientdemo.utils.aspect.TracerAspect;

import dagger.Subcomponent;

@Subcomponent
public interface LoggingComponent {
    void inject(BaseActivity injected);
    void inject(BaseDialog injected);
    void inject(BaseDialogFragment injected);
    void inject(BaseFragment injected);
    void inject(BaseIntentService injected);
    void inject(BaseService injected);

    void inject(TracerAspect tracerAspect);

    @Subcomponent.Builder
    interface Builder {
        LoggingComponent build();
    }
}
