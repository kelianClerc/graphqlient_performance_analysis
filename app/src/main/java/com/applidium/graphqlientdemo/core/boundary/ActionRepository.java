package com.applidium.graphqlientdemo.core.boundary;

import com.applidium.graphqlientdemo.core.entity.Action;

import java.util.List;

public interface ActionRepository {
    List<Action> getActions(String userId);
}
