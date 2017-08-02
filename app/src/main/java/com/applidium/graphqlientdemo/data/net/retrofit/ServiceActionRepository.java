package com.applidium.graphqlientdemo.data.net.retrofit;

import android.util.Log;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.entity.ActionDetail;
import com.applidium.graphqlientdemo.core.entity.ActionDetailBuilder;
import com.applidium.graphqlientdemo.core.entity.Item;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.core.entity.ResponseWithDataBuilder;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;
import com.applidium.graphqlientdemo.data.net.common.RequestManager;
import com.applidium.graphqlientdemo.data.net.retrofit.mapper.RestActionsMapper;
import com.applidium.graphqlientdemo.data.net.retrofit.mapper.RestItemMapper;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestAction;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestActionContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestActionDetailContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItem;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItemContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItemsContent;
import com.applidium.graphqlientdemo.utils.logging.DataAnalysisListener;
import com.applidium.graphqlientdemo.utils.logging.DataAnalyzer;
import com.applidium.graphqlientdemo.utils.logging.InterceptorDataAnalysis;
import com.applidium.graphqlientdemo.utils.logging.RequestType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class ServiceActionRepository implements ActionRepository, DataAnalysisListener {
    private final GraphqldemoService service;
    private final RequestManager requestManager;
    private final RestActionsMapper restActionMapper;
    private final RestItemMapper itemMapper;
    private static final Map<Double, DataAnalyzer> dataSet = new HashMap<>();
    private final InterceptorDataAnalysis interceptorDataAnalysis;

    @Inject
    ServiceActionRepository(
        GraphqldemoService service, RequestManager requestManager,
        RestActionsMapper restActionMapper, RestItemMapper itemMapper, InterceptorDataAnalysis
            interceptorDataAnalysis) {
        this.service = service;
        this.requestManager = requestManager;
        this.restActionMapper = restActionMapper;
        this.itemMapper = itemMapper;
        this.interceptorDataAnalysis = interceptorDataAnalysis;
        this.interceptorDataAnalysis.addListener(this);
    }

    @Override
    public ResponseWithData<List<Action>> getActions(String userId, String activityName) throws
        ServerClientException, UnexpectedException, NetworkException, ServerException, IOException {

        DataAnalyzer log = new DataAnalyzer(RequestType.REST, activityName);
        dataSet.put(log.getSalt(), log);
        Call<RestActionContent> call = service.getActions(userId, log.getSalt());

        Response<RestActionContent> response = call.execute();
        RestActionContent responseContent = null;
        if (response.isSuccessful()) {
            responseContent = response.body();
        }

        List<RestItem> lastItems = new ArrayList<>();
        for (RestAction action : responseContent.actions()) {
            int lastIndex = action.items().size() - 1;
            if (lastIndex < 0) {
                lastItems.add(null);
                continue;
            }
            Call<RestItemContent> callItem = service.getItem(action.items().get(lastIndex).id(), log.getSalt());
            Response<RestItemContent> responseItem = callItem.execute();
            RestItemContent responseItemContent = null;
            if (responseItem.isSuccessful()) {
                responseItemContent = responseItem.body();
            }
            lastItems.add(responseItemContent.item());
        }

        List<Action> actions = restActionMapper.mapList(responseContent.actions(), lastItems);
        ResponseWithData<List<Action>> result = new ResponseWithDataBuilder<List<Action>>()
            .data(actions)
            .logData(log)
            .build();
        dataSet.remove(log.getSalt());
        return result;
    }

    @Override
    public ResponseWithData<ActionDetail> getActionDetail(String actionId, String activityName)
        throws ServerClientException, UnexpectedException, NetworkException, ServerException,
        IOException {
        DataAnalyzer log = new DataAnalyzer(RequestType.REST, activityName);
        dataSet.put(log.getSalt(), log);
        Call<RestActionDetailContent> call = service.getActionDetail(actionId, log.getSalt());

        Response<RestActionDetailContent> response = call.execute();
        RestActionDetailContent responseContent = null;
        if (response.isSuccessful()) {
            responseContent = response.body();
        }

        ActionDetailBuilder builder = new ActionDetailBuilder();
        RestAction action = responseContent.action();
        builder.id(action.id()).title(action.title());


        Call<RestItemsContent> callItem = service.getItemList(actionId, log.getSalt());
        Response<RestItemsContent> responseItem = callItem.execute();
        RestItemsContent responseItemContent = null;
        if (responseItem.isSuccessful()) {
            responseItemContent = responseItem.body();
        }
        List<Item> item = itemMapper.mapList(responseItemContent.items());
        builder.items(item);
        ActionDetail build = builder.build();
        ResponseWithData<ActionDetail> result = new ResponseWithDataBuilder<ActionDetail>()
            .data(build)
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
