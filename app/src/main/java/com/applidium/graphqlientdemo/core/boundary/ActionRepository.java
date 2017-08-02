package com.applidium.graphqlientdemo.core.boundary;

import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.entity.ActionDetail;
import com.applidium.graphqlientdemo.core.entity.ResponseWithData;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;

import java.io.IOException;
import java.util.List;

public interface ActionRepository {
    ResponseWithData<List<Action>> getActions(String userId, String activityName) throws ServerClientException, UnexpectedException, NetworkException, ServerException, IOException;
    ResponseWithData<ActionDetail> getActionDetail(String actionId, String activityName) throws ServerClientException, UnexpectedException, NetworkException, ServerException, IOException;
}
