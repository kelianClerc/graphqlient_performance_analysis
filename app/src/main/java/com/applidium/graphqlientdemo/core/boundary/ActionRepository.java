package com.applidium.graphqlientdemo.core.boundary;

import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.entity.ActionDetail;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;

import java.util.List;

public interface ActionRepository {
    List<Action> getActions(String userId) throws ServerClientException, UnexpectedException, NetworkException, ServerException;
    ActionDetail getActionDetail(String actionId) throws ServerClientException, UnexpectedException, NetworkException, ServerException;
}
