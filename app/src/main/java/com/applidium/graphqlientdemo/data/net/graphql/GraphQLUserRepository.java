package com.applidium.graphqlientdemo.data.net.graphql;

import android.util.Log;

import com.applidium.graphqlient.GraphQL;
import com.applidium.graphqlient.call.QLResponse;
import com.applidium.graphqlient.converter.gson.GsonConverterFactory;
import com.applidium.graphqlient.exceptions.QLException;
import com.applidium.graphqlientdemo.UserListRequest;
import com.applidium.graphqlientdemo.UserListResponse;
import com.applidium.graphqlientdemo.UserRequest;
import com.applidium.graphqlientdemo.UserResponse;
import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.core.entity.ResponseWithDataBuilder;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.data.net.graphql.mapper.QLUserMapper;
import com.applidium.graphqlientdemo.utils.logging.DataAnalysisListener;
import com.applidium.graphqlientdemo.utils.logging.DataAnalyzer;
import com.applidium.graphqlientdemo.utils.logging.RequestType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;

public class GraphQLUserRepository implements UserRepository, DataAnalysisListener {

    private final GraphQL graphql;
    private final QLUserMapper userMapper;
    private static final Map<Double, DataAnalyzer> dataSet = new HashMap<>();

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
    public ResponseWithData<List<User>> getUsers(String activityName) throws QLException {

        DataAnalyzer log = new DataAnalyzer(RequestType.GRAPHQL, activityName);
        dataSet.put(log.getSalt(), log);

        UserListRequest request = new UserListRequest();
        log.setRequestSent();
        QLResponse<UserListResponse> response = graphql.send(request);
        log.setResponseReceived();
        log.roundTrip();
        List<User> users = userMapper.mapList(response.getResponse().users());
        ResponseWithData<List<User>> result = new ResponseWithDataBuilder<List<User>>()
            .data(users)
            .logData(log)
            .build();
        dataSet.remove(log.getSalt());
        return result;
    }

    @Override
    public ResponseWithData<User> getProfile(String userId, String activityName) throws Exception {
        DataAnalyzer log = new DataAnalyzer(RequestType.GRAPHQL, activityName);
        dataSet.put(log.getSalt(), log);
        UserRequest request = new UserRequest(userId);
        log.setRequestSent();
        QLResponse<UserResponse> response = graphql.send(request);
        log.setResponseReceived();
        log.roundTrip();
        User user = userMapper.mapLight(response.getResponse().user());
        ResponseWithData<User> result = new ResponseWithDataBuilder<User>()
            .data(user)
            .logData(log)
            .build();
        dataSet.remove(log.getSalt());
        return result;
    }

    @Override
    public void interceptSize(long length, long time, boolean isRequest, double id) {
        DataAnalyzer dataAnalyzer = dataSet.get(id);

        Log.d("GRAPHQLD", "Interceptor proc : " + length + ", isRequest : " + isRequest + ", id: " + id);
        if (dataAnalyzer != null) {
            if (isRequest) {
                dataAnalyzer.measureRequestSize(length);
                dataAnalyzer.setRequestSent(time);
                dataAnalyzer.roundTrip();
            } else {
                dataAnalyzer.measureResponseSize(length);
                dataAnalyzer.setResponseReceived(time);
            }
        }
    }
}
