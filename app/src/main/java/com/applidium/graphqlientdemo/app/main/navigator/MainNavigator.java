package com.applidium.graphqlientdemo.app.main.navigator;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.actions.ui.fragment.ActionsFragment;
import com.applidium.graphqlientdemo.app.common.FragmentNavigatorHelper;
import com.applidium.graphqlientdemo.app.profile.ui.activity.ProfileActivity;

import javax.inject.Inject;

public class MainNavigator {

    private final Context context;
    private final com.applidium.graphqlientdemo.app.common.FragmentNavigatorHelper helper;

    @Inject
    MainNavigator(Context context, FragmentNavigatorHelper helper) {
        this.context = context;
        this.helper = helper;
    }

    public void navigateToProfile() {
        Intent intent = ProfileActivity.makeIntent(context);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
            context, R.anim.slide_in, android.R.anim.fade_out);
        context.startActivity(intent, options.toBundle());
    }

    public void navigateToUserList() {
        // TODO (kelianclerc) 20/7/17
    }

    public void navigateToActions() {
        ActionsFragment fragment = ActionsFragment.makeFragment();
        helper.switchToFragment(fragment);
    }
}