package com.example.vakulenko.androidcourse;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

public class MonitoringAcivity extends Activity {

    private TextView textView;

    private CustomService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
        textView = findViewById(R.id.textView);

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MonitoringAcivity.this.service = ((CustomService.LocalBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                MonitoringAcivity.this.service = null;
            }
        };

        this.bindService(CustomService.newIntent(this), serviceConnection, Context.BIND_AUTO_CREATE);

        textView.setText(service.data);
    }
}
