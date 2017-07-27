package com.applidium.graphqlientdemo.app.profile.navigator;

import android.content.Context;
import android.content.Intent;

import com.applidium.graphqlientdemo.app.actions.ui.activity.ActionDetailActivity;

import javax.inject.Inject;

public class ProfileNavigator {
    private final Context context;

    @Inject ProfileNavigator(Context context) {
        this.context = context;
    }

    public void navigateToActionDetail(String id) {
        Intent intent = ActionDetailActivity.makeIntent(context, id);
        context.startActivity(intent);
    }
}
