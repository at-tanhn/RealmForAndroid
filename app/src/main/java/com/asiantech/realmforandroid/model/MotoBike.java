package com.asiantech.realmforandroid.model;

import io.realm.RealmObject;

/**
 * Created by honhattan on 4/13/16.
 */
public class MotoBike extends RealmObject {
    private String name;
    private String color;

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
}
