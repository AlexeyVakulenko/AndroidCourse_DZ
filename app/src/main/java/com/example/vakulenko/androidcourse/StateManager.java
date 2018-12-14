package com.example.vakulenko.androidcourse;

public class StateManager {

    private static StateManager stateManager;

    private State state;

    private StateManager() {
    }

    public static StateManager getInstance() {
        if (stateManager == null) {
            stateManager = new StateManager();
        }
        return stateManager;
    }

    public static StateManager getStateManager() {
        return stateManager;
    }

    public static void setStateManager(StateManager stateManager) {
        StateManager.stateManager = stateManager;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
