package com.applidium.graphqlientdemo.data.net.graphql.mapper;

import com.applidium.graphqlientdemo.ActionListResponse;
import com.applidium.graphqlientdemo.ActionResponse;
import com.applidium.graphqlientdemo.core.entity.Action;
import com.applidium.graphqlientdemo.core.entity.ActionBuilder;
import com.applidium.graphqlientdemo.core.entity.ActionDetail;
import com.applidium.graphqlientdemo.core.entity.ActionDetailBuilder;
import com.applidium.graphqlientdemo.core.entity.Item;
import com.applidium.graphqlientdemo.core.entity.ItemBuilder;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;
import com.applidium.graphqlientdemo.utils.mapper.MapperHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class QLActionMapper implements Mapper<ActionListResponse.Actions,Action> {

    private final MapperHelper mapperHelper;

    @Inject QLActionMapper(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }

    public List<Action> mapList(List<ActionListResponse.Actions> actions) {
        return mapperHelper.mapList(actions, this);
    }

    @Override
    public Action map(ActionListResponse.Actions toMap) {
        return new ActionBuilder()
            .id(toMap.id())
            .numberOfSteps(toMap.items().size())
            .title(toMap.title())
            .lastItem(toMap.items().get(toMap.items().size()-1).name())
            .build();
    }

    public ActionDetail mapDetail(ActionResponse.Action action) {
        return new ActionDetailBuilder()
            .id("")
            .title("")
            .items(mapItems(action.items()))
            .build();
    }

    private List<Item> mapItems(List<ActionResponse.Action.Items> items) {
        List<Item> response = new ArrayList<>();
        for (ActionResponse.Action.Items item : items) {
            Item itemBuilt = new ItemBuilder()
                .id(item.id())
                .duration(item.duration())
                .name(item.name())
                .isDone(Integer.valueOf(item.id()) % 2 == 0)
                .build();
            response.add(itemBuilt);
        }
        return response;
    }
}
