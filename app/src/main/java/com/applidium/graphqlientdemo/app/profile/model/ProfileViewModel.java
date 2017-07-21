package com.applidium.graphqlientdemo.app.profile.model;

import com.applidium.graphqlientdemo.app.actions.model.ActionViewModel;

import java.util.List;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ProfileViewModel {
    String name();
    String firstName();
    String age();
    List<ActionViewModel> actions();
}
