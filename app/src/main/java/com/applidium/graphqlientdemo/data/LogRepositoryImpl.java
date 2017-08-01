package com.applidium.graphqlientdemo.data;

import android.content.Context;

import com.applidium.graphqlientdemo.utils.logging.DataAnalyzer;

import java.io.FileOutputStream;

import javax.inject.Inject;

public class LogRepositoryImpl implements LogRepository {

    private Context context;

    @Inject
    LogRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public void writeLog(DataAnalyzer data) {
        String message = data.toString();
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput("myfile", Context.MODE_APPEND);
            outputStream.write(message.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
