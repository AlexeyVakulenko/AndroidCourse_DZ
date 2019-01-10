package com.example.vakulenko.androidcourse;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements Fragment3.Listener {

    private TextProvider provider;

    public interface TextProvider {
        String getText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provider = (TextProvider) getSupportFragmentManager().findFragmentById(R.id.fragment1);
    }

    @Override
    public String getTextToView() {
        return provider.getText();
    }
}
