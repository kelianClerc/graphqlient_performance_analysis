package com.applidium.graphqlientdemo.data.net.retrofit;

import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;
import com.applidium.graphqlientdemo.data.net.common.RequestManager;
import com.applidium.graphqlientdemo.data.net.retrofit.mapper.RestUserMapper;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUserContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUsersContent;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class ServiceUserRepository implements UserRepository {

    private final GraphqldemoService service;
    private final RestUserMapper restUserMapper;
    private final RequestManager requestManager;

    @Inject
    ServiceUserRepository(
        GraphqldemoService service, RestUserMapper restUserMapper,
        RestUserMapper restUserMapper1, RequestManager requestManager) {
        this.service = service;
        this.restUserMapper = restUserMapper1;
        this.requestManager = requestManager;
    }

    @Override @Trace
    public List<User> getUsers(String activityName) throws IOException, ServerClientException, UnexpectedException, NetworkException, ServerException {
        Call<RestUsersContent> call = service.getUsers();
        RestUsersContent response = requestManager.tryToDoRequest(call);
        return restUserMapper.mapList(response.users());
    }

    @Override
    public User getProfile(String userId) throws Exception {
        Call<RestUserContent> call = service.getProfile(userId);
        RestUserContent response = requestManager.tryToDoRequest(call);
        return restUserMapper.map(response.user());
    }
}
