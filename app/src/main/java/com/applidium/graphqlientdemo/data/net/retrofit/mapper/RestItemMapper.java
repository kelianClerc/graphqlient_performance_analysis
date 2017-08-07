package com.applidium.graphqlientdemo.data.net.retrofit.mapper;

import com.applidium.graphqlientdemo.core.entity.Item;
import com.applidium.graphqlientdemo.core.entity.ItemBuilder;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItem;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;
import com.applidium.graphqlientdemo.utils.mapper.MapperHelper;

import java.util.List;

import javax.inject.Inject;

public class RestItemMapper implements Mapper<RestItem, Item> {

    private final MapperHelper mapperHelper;

    @Inject RestItemMapper(MapperHelper mapperHelper) {

        this.mapperHelper = mapperHelper;
    }

    @Override
    public Item map(RestItem toMap) {
        return new ItemBuilder()
            .id(toMap.id())
            .name(toMap.name())
            .duration(String.valueOf(toMap.duration()))
            .isDone(Integer.valueOf(toMap.id()) % 2 == 0)
            .build();

    }

    public List<Item> mapList(List<RestItem> items) {
        return mapperHelper.mapList(items, this);
    }
}
