package com.applidium.graphqlientdemo.app.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.applidium.graphqlientdemo.R;

import javax.inject.Inject;

public class FragmentNavigatorHelper {

    private final FragmentManager fragmentManager;

    @Inject
    FragmentNavigatorHelper(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void switchToFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
            .replace(R.id.container, fragment)
            .commit();
    }

    public void switchToFragment(Fragment fragment, int inAnim, int outAnim) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
            .setCustomAnimations(inAnim, outAnim)
            .replace(R.id.container, fragment)
            .commit();
    }
}
