package com.applidium.graphqlientdemo.data.net.retrofit.mapper;

import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.core.entity.UserBuilder;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUser;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;
import com.applidium.graphqlientdemo.utils.mapper.MapperHelper;

import java.util.List;

import javax.inject.Inject;

public class RestUserMapper implements Mapper<RestUser,User> {

    private final MapperHelper mapperHelper;

    @Inject
    RestUserMapper(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }

    public List<User> mapList(List<RestUser> toMap) {
        return mapperHelper.mapList(toMap, this);
    }

    @Override
    public User map(RestUser toMap) {
        return new UserBuilder()
            .id(toMap.id())
            .name(toMap.name())
            .firstName(toMap.firstname())
            .age(toMap.age())
            .numberOfArticles(toMap.actions().size())
            .build();
    }
}
