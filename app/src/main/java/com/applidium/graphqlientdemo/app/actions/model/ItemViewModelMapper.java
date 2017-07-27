package com.applidium.graphqlientdemo.app.actions.model;

import android.content.Context;

import com.applidium.graphqlientdemo.R;
import com.applidium.graphqlientdemo.core.entity.Item;
import com.applidium.graphqlientdemo.utils.mapper.Mapper;
import com.applidium.graphqlientdemo.utils.mapper.MapperHelper;

import java.util.List;

import javax.inject.Inject;

public class ItemViewModelMapper implements Mapper<Item, ItemViewModel> {

    private final Context context;
    private final MapperHelper mapperHelper;

    @Inject ItemViewModelMapper(Context context, MapperHelper mapperHelper) {
        this.context = context;
        this.mapperHelper = mapperHelper;
    }

    public List<ItemViewModel> mapList(List<Item> toMap) {
        return mapperHelper.mapList(toMap, this);
    }

    @Override
    public ItemViewModel map(Item toMap) {
        return new ItemViewModelBuilder()
            .id(toMap.id())
            .name(toMap.name())
            .duration(context.getString(R.string.item_duration,toMap.duration()))
            .isDone(toMap.isDone())
            .build();
    }
}
