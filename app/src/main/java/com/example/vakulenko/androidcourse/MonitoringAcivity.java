package com.example.vakulenko.androidcourse;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;
import android.util.Log;


import static com.example.vakulenko.androidcourse.CustomService.DELAY;

public class MonitoringAcivity extends Activity {

    private TextView textView;

    private CustomService.LocalBinder binder;

    private Handler handler = new Handler();

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            if (binder != null) {
                textView.setText(String.valueOf(binder.getService().data));
            }
            refresh();
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MonitoringAcivity.this.binder = ((CustomService.LocalBinder) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            MonitoringAcivity.this.binder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
        textView = findViewById(R.id.textView);
        Log.d("", "Попытка подключения к сервису");
        boolean flag = this.bindService(CustomService.newIntent(this), serviceConnection, Context.BIND_ALLOW_OOM_MANAGEMENT);
        Log.d("", String.format("Статус подключения: %b, ", flag));

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        handler.postDelayed(task, DELAY);
    }
}
