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

import static com.example.vakulenko.androidcourse.StateIntentService.CURRENT_STATE_ACTION;
import static com.example.vakulenko.androidcourse.StateIntentService.SET_NEW_STATE_ACTION;
import static com.example.vakulenko.androidcourse.StateIntentService.STATE;

/**
 * Activity, с кнопкой смены состояния и текстовым полем, показывающем текущее состояние сервиса
 */
public class MainActivity extends Activity {
    private static final String CURRENT_STATE = "CURRENT_STATE_ACTION";
    private TextView stateTextView;
    private Button newStateButton;
    private BroadcastReceiver receiver;
    private State currentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateTextView = findViewById(R.id.stateTextView);
        newStateButton = findViewById(R.id.newStateButton);
        newStateButton.setOnClickListener(new ChangeStateListener());
        receiver = new StateReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(CURRENT_STATE_ACTION));
        stateTextView.setText(currentState == null ? "" : currentState.name());
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private void sendNewState(State newState) {
        Intent intent = new Intent();
        intent.setPackage(this.getPackageName());
        intent.setAction(SET_NEW_STATE_ACTION);
        intent.putExtra(STATE, newState);
        startService(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STATE, currentState == null ? -1 : currentState.ordinal());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int stateOrdinal = savedInstanceState.getInt(CURRENT_STATE);
        currentState = stateOrdinal == -1 ? null : State.values()[stateOrdinal];
    }

    public class ChangeStateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            sendNewState(State.getNextState(currentState));
        }
    }

    public class StateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                currentState = State.valueOf(intent.getSerializableExtra(STATE).toString());
                stateTextView.setText(currentState.name());
            }
        }
    }
}
