package com.applidium.graphqlientdemo.data;

import android.support.annotation.StringRes;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.core.boundary.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServiceUserRepository implements UserRepository {

    @Inject
    ServiceUserRepository( ) {}

    @Override @StringRes
    public int getExampleMessage() {
        return R.string.read_me;
    }
}
