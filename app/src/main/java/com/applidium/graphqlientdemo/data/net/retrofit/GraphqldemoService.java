package com.applidium.graphqlientdemo.data.net.retrofit;

import com.applidium.graphqlientdemo.data.net.retrofit.model.RestActionContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestActionDetailContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestItemContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUserContent;
import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUsersContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GraphqldemoService {
    @GET("users")
    Call<RestUsersContent> getUsers();

    @GET("users/{user_id}/actions")
    Call<RestActionContent> getActions(
        @Path("user_id") String userId
    );

    @GET("items/{item_id}")
    Call<RestItemContent> getItem(
        @Path("item_id") String id
    );

    @GET("users/{user_id}")
    Call<RestUserContent> getProfile(
        @Path("user_id") String userId
    );

    @GET("actions/{action_id}")
    Call<RestActionDetailContent> getActionDetail(
        @Path("action_id") String actionId
    );
}
