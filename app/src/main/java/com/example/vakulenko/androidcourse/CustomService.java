package com.example.vakulenko.androidcourse;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class CustomService extends Service {

    public static final int MODE = Service.START_NOT_STICKY;

    public IBinder binder = new LocalBinder();

    public volatile int data;

    public CustomService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dataGenerate();
            }
        }).start();
        return MODE;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);

    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);

    }

    public static final Intent newIntent(Context context) {
        return new Intent(context, CustomService.class);
    }

    private void dataGenerate() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                data = new Random().nextInt();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class LocalBinder extends Binder {
        CustomService getService() {
            return CustomService.this;
        }
    }
}
