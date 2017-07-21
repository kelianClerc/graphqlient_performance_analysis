package com.applidium.graphqlientdemo.data.net.retrofit;

import com.applidium.graphqlientdemo.data.net.retrofit.model.RestUsersContent;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GraphqldemoService {
    @GET("users")
    Call<RestUsersContent> getUsers();
}
