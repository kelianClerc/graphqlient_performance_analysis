package com.applidium.graphqlientdemo.data.net.retrofit;

import android.util.Log;

import com.applidium.graphqlientdemo.core.boundary.UserRepository;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.core.entity.ResponseWithDataBuilder;
import com.applidium.graphqlientdemo.core.entity.User;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;
import com.applidium.graphqlientdemo.data.net.common.RequestManager;
import com.applidium.graphqlientdemo.data.net.retrofit.mapper.RestUserMapper;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUserContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUsersContent;
import com.applidium.graphqlientdemo.utils.logging.DataAnalysisListener;
import com.applidium.graphqlientdemo.utils.logging.DataAnalyzer;
import com.applidium.graphqlientdemo.utils.logging.InterceptorDataAnalysis;
import com.applidium.graphqlientdemo.utils.logging.RequestType;
import com.applidium.graphqlientdemo.utils.trace.Trace;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;

@Singleton
public class ServiceUserRepository implements UserRepository, DataAnalysisListener {

    private final GraphqldemoService service;
    private final RestUserMapper restUserMapper;
    private final RequestManager requestManager;
    private static final Map<Double, DataAnalyzer> dataSet = new HashMap<>();
    private final InterceptorDataAnalysis interceptorDataAnalysis;

    @Inject
    ServiceUserRepository(
        GraphqldemoService service,
        RestUserMapper restUserMapper,
        RequestManager requestManager,
        InterceptorDataAnalysis interceptorDataAnalysis) {
        this.service = service;
        this.restUserMapper = restUserMapper;
        this.requestManager = requestManager;
        this.interceptorDataAnalysis = interceptorDataAnalysis;
        this.interceptorDataAnalysis.addListener(this);
    }

    @Override @Trace
    public ResponseWithData<List<User>> getUsers(String activityName) throws IOException, ServerClientException, UnexpectedException, NetworkException, ServerException {
        DataAnalyzer log = new DataAnalyzer(RequestType.REST, activityName);
        dataSet.put(log.getSalt(), log);
        Call<RestUsersContent> call = null;
        call = service.getUsers(log.getSalt());

        Response<RestUsersContent> response = call.execute();
        RestUsersContent responseContent = null;
        if (response.isSuccessful()) {
            responseContent = response.body();
        }

        List<User> users = restUserMapper.mapList(responseContent.users());
        ResponseWithData<List<User>> result = new ResponseWithDataBuilder<List<User>>()
            .data(users)
            .logData(log)
            .build();

        log.setLabel("Users + Actions");
        dataSet.remove(log.getSalt());

        return result;
    }

    @Override
    public ResponseWithData<User> getProfile(String userId, String activityName) throws Exception {
        DataAnalyzer log = new DataAnalyzer(RequestType.REST, activityName);
        dataSet.put(log.getSalt(), log);
        Call<RestUserContent> call = service.getProfile(userId, log.getSalt());
        Response<RestUserContent> response = call.execute();
        RestUserContent responseContent = null;
        if (response.isSuccessful()) {
            responseContent = response.body();
        }

        User user = restUserMapper.map(responseContent.user());
        ResponseWithData<User> result = new ResponseWithDataBuilder<User>()
            .data(user)
            .logData(log)
            .build();

        log.setLabel("User " + userId);
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
