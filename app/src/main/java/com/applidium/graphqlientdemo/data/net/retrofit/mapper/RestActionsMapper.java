package com.applidium.graphqlientdemo.data.net.retrofit.mapper;

import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.entity.ActionBuilder;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestAction;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RestActionsMapper {

    @Inject
    RestActionsMapper() {
    }

    public Action map(RestAction toMap, RestItem lastItem) {
        ActionBuilder builder= new ActionBuilder();
        builder.id(toMap.id())
               .title(toMap.title())
               .numberOfSteps(toMap.items().size());
        if (lastItem != null) {
            builder.lastItem(lastItem.name());
            builder.lastDuration(lastItem.duration());
            builder.isDone((Integer.valueOf(lastItem.id()) % 2) == 0);
        }
        return builder.build();
    }

    public List<Action> mapList(List<RestAction> actions, List<RestItem> lastItems) {
        List<Action> result = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            RestAction currentAction = actions.get(i);
            RestItem currentItem = lastItems.get(i);
            result.add(map(currentAction, currentItem));
        }
        return result;
    }
}
