package com.applidium.graphqlientdemo.utils.logging;

import org.joda.time.DateTime;

public class DataAnalyzer {
    private String activityName;
    private long requestSent = 0;
    private long responseReceived;
    private long tempRequestSent;
    private long tempResponseReceived;
    private long duration;
    private long requestSize = 0;
    private long responseSize = 0;
    private int roundTrip = 0;
    private int percent = 0;
    private RequestType requestType;
    private String label = "gen";

    private double salt;

    public DataAnalyzer(RequestType requestType, String activityName) {
        this.requestType = requestType;
        this.activityName = activityName;
        salt = Math.floor(Math.random() * 10000000);
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setRequestSent() {
        if (requestSent == 0) {
            this.requestSent = DateTime.now().getMillis();
        }
        tempRequestSent = DateTime.now().getMillis();
    }

    public void setRequestSent(long time) {
        if (requestSent == 0) {
            this.requestSent = time;
        }
        tempRequestSent = time;
    }

    public void setResponseReceived() {
        this.responseReceived = DateTime.now().getMillis();
        tempResponseReceived = responseReceived;
        duration += tempResponseReceived - tempRequestSent;
    }

    public void setResponseReceived(long time) {
        this.responseReceived = time;
        tempResponseReceived = responseReceived;
        duration += tempResponseReceived - tempRequestSent;
    }

    public void measureRequestSize(long length) {
        this.requestSize += length;
    }

    public void measureResponseSize(long length) {
        this.responseSize += length;
    }

    public void roundTrip() {
        this.roundTrip++;
    }

    @Override
    public String toString() {
        int requestType = this.requestType == RequestType.GRAPHQL ? 2 : 1;
        String message = activityName + "###" + requestSent + "###" + responseReceived + "###" + duration;
        message += "###" + requestSize + "###" + responseSize + "###" + roundTrip + "###";
        message += percent + "###" + requestType + "###" + label + "\n";

        return message;
    }

    public double getSalt() {
        return salt;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
