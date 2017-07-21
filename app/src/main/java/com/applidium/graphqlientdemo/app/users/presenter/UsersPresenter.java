package com.applidium.graphqlientdemo.app.users.presenter;

import com.applidium.graphqlientdemo.app.common.Presenter;
import com.applidium.graphqlientdemo.app.users.model.UserViewModel;
import com.applidium.graphqlientdemo.app.users.model.UserViewModelBuilder;
import com.applidium.graphqlientdemo.app.users.ui.UsersViewContract;

import java.util.Arrays;

import javax.inject.Inject;

public class UsersPresenter extends Presenter<UsersViewContract> {
    @Inject UsersPresenter(UsersViewContract view) {
        super(view);
    }

    @Override
    public void start() {
        doStub();
    }

    private void doStub() {
        UserViewModel userViewModel = new UserViewModelBuilder()
            .id("12")
            .name("Kelian")
            .age("22 ans")
            .numberOfActions("0 post")
            .build();
        UserViewModel userViewModel1 = new UserViewModelBuilder()
            .id("12")
            .name("Robert")
            .age("42 ans")
            .numberOfActions("8 post")
            .build();
        UserViewModel userViewModel2 = new UserViewModelBuilder()
            .id("12")
            .name("Bran")
            .age("12 ans")
            .numberOfActions("5 post")
            .build();
        UserViewModel userViewModel3 = new UserViewModelBuilder()
            .id("12")
            .name("John")
            .age("20 ans")
            .numberOfActions("10 post")
            .build();
        UserViewModel userViewModel4 = new UserViewModelBuilder()
            .id("12")
            .name("Arya")
            .age("8 ans")
            .numberOfActions("25 post")
            .build();

        view.showUsers(Arrays.asList(userViewModel1, userViewModel2, userViewModel, userViewModel4, userViewModel3, userViewModel2, userViewModel, userViewModel4, userViewModel3));
    }

    @Override
    public void stop() {

    }

    public void onUser(UserViewModel user) {
        // TODO (kelianclerc) 20/7/17 user
    }
}
