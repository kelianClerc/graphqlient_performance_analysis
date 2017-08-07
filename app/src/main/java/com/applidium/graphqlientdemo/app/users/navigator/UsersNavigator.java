package com.applidium.graphqlientdemo.app.users.navigator;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.app.profile.ui.activity.ProfileActivity;

import javax.inject.Inject;

public class UsersNavigator {
    private final Context context;

    @Inject UsersNavigator(Context context) {
        this.context = context;
    }

    public void navigateToUserProfile(String id) {
        Intent intent = ProfileActivity.makeIntent(context, id);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
            context, R.anim.slide_in, android.R.anim.fade_out);
        context.startActivity(intent, options.toBundle());
    }
}
