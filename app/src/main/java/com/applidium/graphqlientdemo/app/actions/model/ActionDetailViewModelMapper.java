package com.applidium.graphqlientdemo.app.actions.model;

import com.applidium.graphqlientdemo.core.interactor.actions.ActionDetailResponse;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;

import javax.inject.Inject;

public class ActionDetailViewModelMapper implements Mapper<ActionDetailResponse, ActionDetailViewModel> {

    private final ItemViewModelMapper mapper;

    @Inject ActionDetailViewModelMapper(ItemViewModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ActionDetailViewModel map(ActionDetailResponse toMap) {
        return new ActionDetailViewModelBuilder()
            .id(toMap.id())
            .title(toMap.title())
            .items(mapper.mapList(toMap.items()))
            .build();
    }
}
