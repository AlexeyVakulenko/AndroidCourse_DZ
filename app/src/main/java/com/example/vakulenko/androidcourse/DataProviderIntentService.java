package com.example.vakulenko.androidcourse;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.Date;

/**
 * IntentService генерирующий слаучайные данные через каждый TIMEOUT и отправляет их ввиде широковещательного сообщения
 */
public class DataProviderIntentService extends IntentService {
    public static final String ACTION_START_GENERATE = "com.example.vakulenko.androidcourse.action.START_GENERATE";
    public static final String ACTION_DATA = "com.example.vakulenko.androidcourse.action.DATA";
    public static final String DATA = "com.example.vakulenko.androidcourse.extra.DATA";
    public static final String TAG = "DataProviderIntentService";
    public static final int TIMEOUT = 2500;

    public DataProviderIntentService() {
        super(TAG);
    }

    public static void startGenerate(Context context) {
        Intent intent = new Intent(context, DataProviderIntentService.class);
        intent.setAction(ACTION_START_GENERATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_GENERATE.equals(action)) {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(TIMEOUT);
                        String data = generateData();
                        Intent broadcastIntent = new Intent();
                        broadcastIntent.putExtra(DATA, data);
                        broadcastIntent.setAction(ACTION_DATA);
                        sendBroadcast(broadcastIntent);
                    }
                } catch (InterruptedException e) {
                    Log.e(TAG, "Прерывание потока", e);
                }
            }
        }
    }

    private String generateData() {
        return String.valueOf(new Date().hashCode());
    }
}
