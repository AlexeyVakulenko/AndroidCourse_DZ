package com.example.vakulenko.androidcourse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;
import java.util.Random;

public class MainActivity extends Activity {
    private static final String POS_KEY = "pos";
    private View rect1;
    private View rect2;
    private View rect3;
    private View rect4;
    private View rect5;
    private View rect6;
    private Button restartButton;
    private TextView textView;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POS_KEY, pos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pos = savedInstanceState.getInt(POS_KEY);
    }

    public void restart() {
        setContentView(R.layout.activity_main);
        rect1 = findViewById(R.id.rect1);
        rect2 = findViewById(R.id.rect2);
        rect3 = findViewById(R.id.rect3);
        rect4 = findViewById(R.id.rect4);
        rect5 = findViewById(R.id.rect5);
        rect6 = findViewById(R.id.rect6);
        textView = findViewById(R.id.textView);
        restartButton = findViewById(R.id.restartButton);
        rect1.setOnClickListener(new ClickListener(rect4));
        rect2.setOnClickListener(new ClickListener(rect5));
        rect3.setOnClickListener(new ClickListener(rect6));
        restartButton.setOnClickListener(new RestartListener());
        pos = circlePos();
    }

    private int circlePos() {
        Random random = new Random(new Date().getTime());
        int res;
        int first = random.nextBoolean() ? 1 : 0;
        int second = random.nextBoolean() ? 1 : 0;
        res = first + second;
        switch (res) {
            case 0:
                res = R.id.rect1;
                break;
            case 1:
                res = R.id.rect2;
                break;
            case 2:
                res = R.id.rect3;
                break;
        }
        return res;
    }

    private class ClickListener implements View.OnClickListener {
        private View boundView;

        public ClickListener(View boundView) {
            this.boundView = boundView;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == pos) {
                v.setBackgroundResource(R.drawable.shape_oval);
                boundView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.setText("Победа!");
            } else {
                v.setVisibility(View.INVISIBLE);
                boundView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.setText("Нет. Попробуйте еще раз..");
            }
            rect1.setEnabled(false);
            rect2.setEnabled(false);
            rect3.setEnabled(false);
            rect4.setEnabled(false);
            rect5.setEnabled(false);
            rect6.setEnabled(false);
            restartButton.setVisibility(View.VISIBLE);
        }
    }

    private class RestartListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            restart();
        }
    }
}
