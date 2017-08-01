package com.applidium.graphqlientdemo.utils.logging;

import android.content.Context;

import org.joda.time.DateTime;

import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class DataAnalyzer {
    private String activityName;
    private final Context context;
    private long requestSent;
    private long responseReceived;
    private long requestSize;
    private long responseSize;
    private int roundTrip = 0;
    private int percent = 0;
    private RequestType requestType;
    private String label = "gen";

    public DataAnalyzer(RequestType requestType, String activityName, Context context) {
        this.requestType = requestType;
        this.activityName = activityName;
        this.context = context;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setRequestSent() {
        this.requestSent = DateTime.now().getMillis();
    }

    public void setResponseReceived() {
        this.responseReceived = DateTime.now().getMillis();
    }

    public <T> void mesureSize(Call<T> call, Response<T> response) {
        try {
            this.requestSize = call.request().body().contentLength();
            this.responseSize = response.raw().body().contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void roundTrip() {
        this.roundTrip++;
    }

    public void writeLog() {
        String message = toString();
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput("myfile", Context.MODE_PRIVATE);
            outputStream.write(message.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        int requestType = this.requestType == RequestType.GRAPHQL ? 2 : 1;
        String message = activityName + " ###" + requestSent + "###" + responseReceived;
        message += "###" + requestSize + "###" + responseSize + "###" + roundTrip + "###";
        message += percent + "###" + requestType + "###" + label;

        return message;
    }
}
