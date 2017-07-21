package com.applidium.graphqlientdemo.app.users.model;

import android.content.Context;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.core.interactor.users.UserResponse;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;
import com.applidium.graphqlientdemo.utils.mapper.MapperHelper;

import java.util.List;

import javax.inject.Inject;

public class UserMapper implements Mapper<UserResponse, UserViewModel> {

    private final Context context;
    private final MapperHelper mapperHelper;

    @Inject UserMapper(Context context, MapperHelper mapperHelper) {
        this.context = context;
        this.mapperHelper = mapperHelper;
    }

    public List<UserViewModel> mapList(List<UserResponse> toMap) {
        return mapperHelper.mapList(toMap, this);
    }

    @Override
    public UserViewModel map(UserResponse toMap) {
        return new UserViewModelBuilder()
            .id(toMap.id())
            .name(toMap.name())
            .age(context.getResources().getString(R.string.age_label, String.valueOf(toMap.age())))
            .numberOfActions(context.getResources().getString(R.string.actions_label, String.valueOf(toMap.numberOfActions())))
            .build();
    }
}
