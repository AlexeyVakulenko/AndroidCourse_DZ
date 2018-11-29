package com.example.vakulenko.androidcourse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private View button1;
    private View button2;
    private View button3;

    private View button4;
    private View button5;
    private View button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        button1.setOnClickListener(new ToggleListener(button4));
        button2.setOnClickListener(new ToggleListener(button5));
        button3.setOnClickListener(new ToggleListener(button6));

        button4.setOnClickListener(new ToggleListener(button1));
        button5.setOnClickListener(new ToggleListener(button2));
        button6.setOnClickListener(new ToggleListener(button3));
    }

    private class ToggleListener implements View.OnClickListener {
        private View boundView;

        public ToggleListener(View boundView) {
            this.boundView = boundView;
        }

        @Override
        public void onClick(View v) {
            int visibility = v.getVisibility();
            v.setVisibility(boundView.getVisibility());
            boundView.setVisibility(visibility);
        }
    }
}
