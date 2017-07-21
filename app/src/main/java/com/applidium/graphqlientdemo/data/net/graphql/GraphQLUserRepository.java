package com.applidium.graphqlientdemo.data.net.graphql;

import com.applidium.graphqlient.GraphQL;
import com.applidium.graphqlient.call.QLResponse;
import com.applidium.graphqlient.converter.gson.GsonConverterFactory;
import com.applidium.graphqlient.exceptions.QLException;
import com.applidium.graphqlientdemo.UserListRequest;
import com.applidium.graphqlientdemo.UserListResponse;
import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.data.net.graphql.mapper.QLUserMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;

import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;

public class GraphQLUserRepository implements UserRepository {

    private final GraphQL graphql;
    private final QLUserMapper userMapper;

    @Inject
    GraphQLUserRepository(QLUserMapper userMapper) {
        this.userMapper = userMapper;
        Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
            .create();
        GsonConverterFactory converterFactory = GsonConverterFactory.create(gson);
        String url = "http://localhost:3000/graphql/test";
        graphql = new GraphQL.Builder()
            .baseUrl(url)
            .converterFactory(converterFactory)
            .build();
    }

    @Override
    public List<User> getUsers() throws QLException {
        UserListRequest request = new UserListRequest();
        QLResponse<UserListResponse> response = graphql.send(request);
        return userMapper.mapList(response.getResponse().users());
    }
}
