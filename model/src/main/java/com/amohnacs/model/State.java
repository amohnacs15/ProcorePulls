package com.amohnacs.model;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public enum State {

    OPEN("open"),
    CLOSED("closed"),
    ALL("all");

    private final String value;

    State(final String text) {
        this.value = text;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
