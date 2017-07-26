package com.applidium.graphqlientdemo.app.actions.model;

import android.content.Context;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.core.interactor.actions.ActionResponse;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;
import com.applidium.graphqlientdemo.utils.mapper.MapperHelper;

import java.util.List;

import javax.inject.Inject;

public class ActionViewModelMapper implements Mapper<ActionResponse, ActionViewModel> {

    private final MapperHelper mapperHelper;
    private final Context context;

    @Inject ActionViewModelMapper(MapperHelper mapperHelper, Context context) {
        this.mapperHelper = mapperHelper;
        this.context = context;
    }

    public List<ActionViewModel> mapList(List<ActionResponse> actionResponses) {
        return mapperHelper.mapList(actionResponses, this);
    }

    @Override
    public ActionViewModel map(ActionResponse toMap) {
        return new ActionViewModelBuilder()
            .id(toMap.id())
            .articleName(toMap.title())
            .numberOfItems(context.getString(R.string.actions_label, String.valueOf(toMap.numberOfSteps())))
            .lastItemTitle(toMap.lastActionName())
            .lastItemDuration(String.valueOf(toMap.duration()) + "secondes")
            .isDone(toMap.isDone())
            .build();
    }
}
