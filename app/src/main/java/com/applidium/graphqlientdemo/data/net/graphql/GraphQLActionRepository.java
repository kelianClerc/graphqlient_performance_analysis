package com.applidium.graphqlientdemo.data.net.graphql;

import android.util.Log;

import com.applidium.graphqlient.GraphQL;
import com.applidium.graphqlient.call.QLCall;
import com.applidium.graphqlient.call.QLResponse;
import com.applidium.graphqlient.converter.gson.GsonConverterFactory;
import com.applidium.graphqlient.exceptions.QLException;
import com.applidium.graphqlientdemo.ActionListRequest;
import com.applidium.graphqlientdemo.ActionListResponse;
import com.applidium.graphqlientdemo.ActionRequest;
import com.applidium.graphqlientdemo.ActionResponse;
import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.entity.ActionDetail;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.core.entity.ResponseWithDataBuilder;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;
import com.applidium.graphqlientdemo.data.net.graphql.mapper.QLActionMapper;
import com.applidium.graphqlientdemo.utils.logging.DataAnalysisListener;
import com.applidium.graphqlientdemo.utils.logging.DataAnalyzer;
import com.applidium.graphqlientdemo.utils.logging.InterceptorDataAnalysis;
import com.applidium.graphqlientdemo.utils.logging.RequestType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import okhttp3.OkHttpClient;

public class GraphQLActionRepository implements ActionRepository, DataAnalysisListener {

    private final GraphQL graphql;
    private final QLActionMapper actionMapper;
    private static final Map<Double, DataAnalyzer> dataSet = new HashMap<>();
    private final InterceptorDataAnalysis interceptorDataAnalysis;

    @Inject
    GraphQLActionRepository(QLActionMapper actionMapper, OkHttpClient client,
                            InterceptorDataAnalysis interceptorDataAnalysis) {
        this.actionMapper = actionMapper;
        this.interceptorDataAnalysis = interceptorDataAnalysis;
        this.interceptorDataAnalysis.addListener(this);
        Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
            .create();
        GsonConverterFactory converterFactory = GsonConverterFactory.create(gson);
        String url = "http://localhost:3000/graphql/test";
        graphql = new GraphQL.Builder()
            .baseUrl(url)
            .converterFactory(converterFactory)
            .client(client)
            .build();
    }

    @Override
    public ResponseWithData<List<Action>> getActions(String userId, String activityName) throws
        ServerClientException, UnexpectedException, NetworkException, ServerException, IOException, QLException
    {
        DataAnalyzer log = new DataAnalyzer(RequestType.GRAPHQL, activityName);
        dataSet.put(log.getSalt(), log);
        ActionListRequest request = new ActionListRequest(userId);
        graphql.addHeader(InterceptorDataAnalysis.QUERY_ID_ANALYSIS, String.valueOf(log.getSalt()));
        QLCall<ActionListResponse> call = graphql.call(request, true);
        QLResponse<ActionListResponse> response = graphql.send(call);
        List<Action> actions = actionMapper.mapList(response.getResponse().actions());
        ResponseWithData<List<Action>> result = new ResponseWithDataBuilder<List<Action>>()
            .data(actions)
            .logData(log)
            .build();
        log.setLabel("Actions + Items for user " + userId);
        dataSet.remove(log.getSalt());
        return result;
    }

    @Override
    public ResponseWithData<ActionDetail> getActionDetail(String actionId, String activityName) throws
        ServerClientException, UnexpectedException, NetworkException, ServerException, IOException, QLException {
        DataAnalyzer log = new DataAnalyzer(RequestType.GRAPHQL, activityName);
        dataSet.put(log.getSalt(), log);
        ActionRequest request = new ActionRequest(actionId);
        graphql.addHeader(InterceptorDataAnalysis.QUERY_ID_ANALYSIS, String.valueOf(log.getSalt()));
        QLCall<ActionResponse> call = graphql.call(request, true);
        QLResponse<ActionResponse> response = graphql.send(call);
        ActionDetail action = actionMapper.mapDetail(response.getResponse().action());
        ResponseWithData<ActionDetail> result = new ResponseWithDataBuilder<ActionDetail>()
            .data(action)
            .logData(log)
            .build();
        log.setLabel("Items for action " + actionId);
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
