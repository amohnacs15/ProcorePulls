package com.amohnacs.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public enum State {

    @SerializedName("open")
    OPEN,
    @SerializedName("closed")
    CLOSED,
    @SerializedName("all")
    ALL;
/*
    State(final String text) {
        this.value = text;
    }

    @Override
    public String toString() {
        return value.toString();
    }
    */
}
