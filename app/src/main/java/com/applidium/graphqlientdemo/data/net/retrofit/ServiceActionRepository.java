package com.applidium.graphqlientdemo.data.net.retrofit;

import com.applidium.graphqlientdemo.core.boundary.ActionRepository;
import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;
import com.applidium.graphqlientdemo.data.net.common.RequestManager;
import com.applidium.graphqlientdemo.data.net.retrofit.mapper.RestActionsMapper;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestAction;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestActionContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItem;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItemContent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class ServiceActionRepository implements ActionRepository {
    private final GraphqldemoService service;
    private final RequestManager requestManager;
    private final RestActionsMapper restActionMapper;

    @Inject
    ServiceActionRepository(GraphqldemoService service, RequestManager requestManager,
                            RestActionsMapper restActionMapper) {
        this.service = service;
        this.requestManager = requestManager;
        this.restActionMapper = restActionMapper;
    }

    @Override
    public List<Action> getActions(String userId) throws
        ServerClientException, UnexpectedException, NetworkException, ServerException
    {
        Call<RestActionContent> call = service.getActions(userId);
        RestActionContent response = requestManager.tryToDoRequest(call);
        List<RestItem> lastItems = new ArrayList<>();
        for (RestAction action : response.actions()) {
            int lastIndex = action.items().size() - 1;
            if (lastIndex < 0) {
                lastItems.add(null);
                continue;
            }
            Call<RestItemContent> callItem = service.getItem(action.items().get(lastIndex).id());
            RestItemContent itemResponse = requestManager.tryToDoRequest(callItem);
            lastItems.add(itemResponse.item());
        }
        return restActionMapper.mapList(response.actions(), lastItems);
    }
}
