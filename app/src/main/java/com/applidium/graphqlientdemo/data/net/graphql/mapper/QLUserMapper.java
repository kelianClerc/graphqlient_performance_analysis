package com.applidium.graphqlientdemo.data.net.graphql.mapper;

import com.applidium.graphqlientdemo.UserListResponse;
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
            .build();
    }

    public List<User> mapList(List<UserListResponse.Users> users) {
        return mapperHelper.mapList(users, this);
    }
}
