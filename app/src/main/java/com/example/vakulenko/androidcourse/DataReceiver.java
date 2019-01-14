package com.example.vakulenko.androidcourse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Получатель для данных, которые генерирует DataProviderIntentService
 * @see DataProviderIntentService
 */
public class DataReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra(DataProviderIntentService.DATA);
        if (data != null) {
            ((TextSetable) context).setData(data);
        }
    }

    public interface TextSetable {
        void setData(String data);
    }
}
