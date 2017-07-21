package com.applidium.graphqlientdemo.core.boundary;

import com.applidium.graphqlient.exceptions.QLException;
import com.applidium.graphqlientdemo.core.error.exceptions.NetworkException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerClientException;
import com.applidium.graphqlientdemo.core.error.exceptions.ServerException;
import com.applidium.graphqlientdemo.core.error.exceptions.UnexpectedException;

import java.io.IOException;
import java.util.List;

public interface UserRepository {
    List<com.applidium.graphqlientdemo.core.entity.User> getUsers() throws QLException, IOException, ServerClientException, UnexpectedException, NetworkException, ServerException;
}
