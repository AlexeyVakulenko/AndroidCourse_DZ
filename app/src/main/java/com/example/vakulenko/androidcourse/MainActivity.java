package com.example.vakulenko.androidcourse;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.vakulenko.androidcourse.StateIntentService.SET_NEW_STATE;
import static com.example.vakulenko.androidcourse.StateIntentService.STATE;

public class MainActivity extends Activity {
    private TextView stateTextView;
    private Button newStateButton;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateTextView = findViewById(R.id.stateTextView);
        newStateButton = findViewById(R.id.newStateButton);

        newStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stateText = stateTextView.getText().toString();
                State currentState = null;
                if (stateText != null && !stateText.isEmpty()) {
                    currentState = State.valueOf(stateText);
                }
                sendNewState(State.getNextState(currentState));
            }
        });
        receiver = new Receiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("com.example.vakulenko.androidcourse.CURRENT_STATE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private void sendNewState(State newState) {
        Intent intent = new Intent();
        intent.setPackage(this.getPackageName());
        intent.setAction(SET_NEW_STATE);
        intent.putExtra(STATE, newState);
        startService(intent);
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                stateTextView.setText(intent.getSerializableExtra(STATE).toString());
            }
        }
    }
}
