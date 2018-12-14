package com.example.vakulenko.androidcourse;

import android.app.IntentService;
import android.content.Intent;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class StateIntentService extends IntentService {
    public static final String STATE = "STATE";
    public static final String CURRENT_STATE = "com.example.vakulenko.androidcourse.CURRENT_STATE";
    public static final String SET_NEW_STATE = "com.example.vakulenko.androidcourse.SET_NEW_STATE";

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
        broadCastIntent.setAction(CURRENT_STATE);
        broadCastIntent.putExtra(STATE, newState);
        sendBroadcast(broadCastIntent);
    }
}
