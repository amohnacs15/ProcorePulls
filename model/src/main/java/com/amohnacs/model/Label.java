package com.amohnacs.model;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

class Label {

    private String name;
    private String color;
    private boolean isDefault;

    public Label() {
    }

    public Label(String name, String color, boolean isDefault) {
        this.name = name;
        this.color = color;
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
