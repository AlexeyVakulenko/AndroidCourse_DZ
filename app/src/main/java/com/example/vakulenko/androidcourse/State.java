package com.example.vakulenko.androidcourse;

public enum State {
    A, B, C, D, E;

    public static State getNextState(State state) {
        State[] states = State.values();
        return (state == null || state.ordinal() >= states.length - 1) ? states[0] : states[state.ordinal() + 1];
    }
}
