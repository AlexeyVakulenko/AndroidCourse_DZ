package com.example.vakulenko.androidcourse;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import org.jetbrains.annotations.Nullable;
import android.util.Log;

import java.util.Random;

public class CustomService extends Service {

    public static final int DELAY = 1000;

    public static final int MODE = Service.START_NOT_STICKY;

    public IBinder binder = new CustomBinder();

    public volatile int data;

    public CustomService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("","Запуск сервиса");
        new Thread(new Runnable() {
            @Override
            public void run() {
                dataGenerate();
            }
        }).start();
        return MODE;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public static final Intent newIntent(Context context) {
        return new Intent(context, CustomService.class);
    }

    private void dataGenerate() {
        Log.d("Старт генерации данных", "");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                data = new Random().nextInt();
                Log.d(": ", String.valueOf(data));
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Log.e("", "", e);
            }
        }
    }

    public class CustomBinder extends Binder {
        CustomService getService() {
            return CustomService.this;
        }
    }
}
