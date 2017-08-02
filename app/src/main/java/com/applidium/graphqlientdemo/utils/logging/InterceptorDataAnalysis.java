package com.applidium.graphqlientdemo.utils.logging;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InterceptorDataAnalysis implements Interceptor {

    public static final String QUERY_ID_ANALYSIS = "QueryIdAnalysis";
    private static final List<DataAnalysisListener> listeners = new ArrayList<>();

    @Inject
    public InterceptorDataAnalysis() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        MediaType contentType = response.body().contentType();

        String bodyString = response.body().string();


        Log.d("GRAPHQLD", "I am the interceptor");
        Log.d("GRAPHQLD", "I have a request : " + request.url().toString().getBytes().length);
        int length = bodyString.getBytes().length;
        Log.d("GRAPHQLD", "I have a response : " + length);
        if (listeners != null) {
            Log.d("GRAPHQLD", "I do my job");
            double requestId = getRequestId(request);
            Log.d("GRAPHQLD", "I read " + requestId + " received : " + response.receivedResponseAtMillis() + " sent : " + response.sentRequestAtMillis());
            sendToListeners(request.url().toString().getBytes().length, response.sentRequestAtMillis(), length, response.receivedResponseAtMillis(), requestId);

        }
        ResponseBody body = ResponseBody.create(contentType, bodyString);
        return response.newBuilder().body(body).build();
    }

    private void sendToListeners(long requestLength, long timeRequest, long responseLength, long timeResponse, double requestId) {
        Log.d("GRAPHQLD", "I have " + listeners.size());
        for (DataAnalysisListener listener : listeners) {

            listener.interceptSize(requestLength, timeRequest, true, requestId);
            listener.interceptSize(responseLength, timeResponse, false, requestId);
        }
    }

    private double getRequestId(Request request) {
        return Double.valueOf(request.header(QUERY_ID_ANALYSIS));
    }

    public void addListener(DataAnalysisListener listener) {
        Log.d("GRAPHQLD", "I have to add a listener : " + listener);
        Log.d("GRAPHQLD", "Size before : " + listeners.size());
        listeners.add(listener);
        Log.d("GRAPHQLD", "Size now : " + listeners.size());
    }

    public void removeListener(DataAnalysisListener listener) {
        listeners.remove(listener);
    }
}
