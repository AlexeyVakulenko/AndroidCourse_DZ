package com.example.vakulenko.androidcourse;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements DataProvider, DataReceiver.DataSetable {

    private DataProvider provider;

    private DataReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provider = (DataProvider) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        registerReceiver();
        DataProviderIntentService.startGenerate(this);
    }

    @Override
    public String getText() {
        return provider.getText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void registerReceiver() {
        receiver = new DataReceiver();
        IntentFilter intentFilter = new IntentFilter(
                DataProviderIntentService.ACTION_DATA);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void setData(String data) {
        ((DataReceiver.DataSetable) provider).setData(data);
    }
}
