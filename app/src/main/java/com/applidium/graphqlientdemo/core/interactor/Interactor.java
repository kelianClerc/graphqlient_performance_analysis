package com.applidium.graphqlientdemo.core.interactor;

import com.applidium.graphqlientdemo.utils.threading.RunOnExecutionThread;
import com.applidium.graphqlientdemo.utils.threading.RunOnPostExecutionThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("squid:S00119") // Not applicable
public abstract class Interactor<Listener, Params, Result> {

    protected final List<Listener> listeners;

    protected Interactor() {
        listeners = Collections.synchronizedList(new ArrayList<Listener>());
    }

    @RunOnExecutionThread
    public void execute(Listener listener, Params params) {
        addListener(listener);
        execute(params);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    protected abstract void execute(Params params);

    @RunOnPostExecutionThread
    protected void dispatchResultOnPostExecutionThread(Result result) {
        dispatchResult(result);
    }

    protected abstract void dispatchResult(Result result);

    private void addListener(Listener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
}
