package com.asiantech.realmforandroid.json;

import io.realm.RealmObject;

/**
 * Created by honhattan on 4/18/16.
 */
public class City extends RealmObject {
    private String name;
    private long votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}