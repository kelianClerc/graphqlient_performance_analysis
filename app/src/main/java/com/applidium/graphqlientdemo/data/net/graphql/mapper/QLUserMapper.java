package com.applidium.graphqlientdemo.data.net.graphql.mapper;

import com.applidium.graphqlientdemo.UserListResponse;
import com.applidium.graphqlientdemo.UserResponse;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.core.entity.UserBuilder;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;
import com.applidium.graphqlientdemo.utils.mapper.MapperHelper;

import java.util.List;

import javax.inject.Inject;

public class QLUserMapper implements Mapper<UserListResponse.Users,User> {

    private final MapperHelper mapperHelper;

    @Inject QLUserMapper(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }

    @Override
    public User map(UserListResponse.Users toMap) {
        return new UserBuilder()
            .name(toMap.name())
            .firstName(toMap.firstname())
            .id(toMap.id())
            .age(toMap.age())
            .numberOfArticles(toMap.actions().size())
            .build();
    }

    public List<User> mapList(List<UserListResponse.Users> users) {
        return mapperHelper.mapList(users, this);
    }

    public User mapLight(UserResponse.User user) {
        return new UserBuilder()
            .id(user.id())
            .name(user.name())
            .firstName(user.firstname())
            .age(user.age())
            .build();
    }
}
