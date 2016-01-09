package com.example.hackathon.random.database.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hackathon on 1/9/16.
 */
public class RealmParticipant extends RealmObject {
    public static final String PARTICIPANT_ID = "name";

    @PrimaryKey
    private String name;
    private String seed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}
