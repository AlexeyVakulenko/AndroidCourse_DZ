package com.example.vakulenko.androidcourse;

import android.app.IntentService;
import android.content.Intent;

/**
 * IntentService смены состояния
 */
public class StateIntentService extends IntentService {
    public static final String STATE = "STATE";
    public static final String CURRENT_STATE_ACTION = "com.example.vakulenko.androidcourse.CURRENT_STATE_ACTION";
    public static final String SET_NEW_STATE_ACTION = "com.example.vakulenko.androidcourse.SET_NEW_STATE_ACTION";

    public StateIntentService() {
        super("StateIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            State newState = (State) intent.getExtras().get(STATE);
            StateManager.getInstance().setState(newState);
            sendNewState(newState);
        }
    }

    private void sendNewState(State newState) {
        Intent broadCastIntent = new Intent();
        broadCastIntent.setAction(CURRENT_STATE_ACTION);
        broadCastIntent.putExtra(STATE, newState);
        sendBroadcast(broadCastIntent);
    }
}
