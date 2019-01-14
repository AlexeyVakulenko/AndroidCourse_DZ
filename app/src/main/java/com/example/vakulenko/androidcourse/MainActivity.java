package com.example.vakulenko.androidcourse;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.example.vakulenko.androidcourse.fragments.Fragment1;
import com.example.vakulenko.androidcourse.fragments.Fragment2;

public class MainActivity extends FragmentActivity implements DataProvider, DataReceiver.TextSetable {

    public static final String EDIT_TEXT_DATA_KEY = "EDIT_TEXT_DATA_KEY";
    public static final String TEXT_VIEW_DATA_KEY = "TEXT_VIEW_DATA_KEY";

    private Fragment1 fragment1;
    private Fragment2 fragment2;

    private DataReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        registerReceiver();
        DataProviderIntentService.startGenerate(this);
    }

    @Override
    public String getText() {
        return ((DataProvider) fragment1).getText();
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
         fragment1.setText(data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_TEXT_DATA_KEY, fragment1.getText());
        outState.putString(TEXT_VIEW_DATA_KEY, fragment2.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fragment1.setText(savedInstanceState.getString(EDIT_TEXT_DATA_KEY));
        fragment1.setText(savedInstanceState.getString(TEXT_VIEW_DATA_KEY));
    }
}
