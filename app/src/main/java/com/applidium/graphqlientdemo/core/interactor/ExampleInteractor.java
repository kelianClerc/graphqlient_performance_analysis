package com.applidium.graphqlientdemo.core.interactor;

import com.applidium.graphqlientdemo.core.boundary.ExampleRepository;

import javax.inject.Inject;

public class ExampleInteractor extends Interactor<ExampleListener, Void, Integer> {

    private final ExampleRepository repository;

    @Inject ExampleInteractor(ExampleRepository repository) {
        super();
        this.repository = repository;
    }

    @Override protected void execute(Void unused) {
        dispatchResultOnPostExecutionThread(repository.getExampleMessage());
    }

    @Override protected void dispatchResult(Integer result) {
        for (ExampleListener listener : listeners) {
            listener.onResult(result);
        }
    }

}
