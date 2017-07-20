package com.applidium.graphqlientdemo.core.interactor;

import android.support.annotation.StringRes;

import com.applidium.graphqlientdemo.core.boundary.ExampleRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExampleInteractorTest {

    @StringRes
    private static final int RESULT = 42;

    @Mock ExampleListener listener;
    @Mock ExampleRepository repository;

    private ExampleInteractor interactor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        interactor = new ExampleInteractor(repository);
    }

    @Test
    public void onExecute_should_getMessage_dispatchResult() throws Exception {
        when(repository.getExampleMessage()).thenReturn(RESULT);

        interactor.execute(listener, null);

        verify(repository).getExampleMessage();
        verify(listener).onResult(RESULT);
    }
}
