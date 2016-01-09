package com.example.hackathon.random.model;

import com.example.hackathon.random.database.helpers.Realmable;
import com.example.hackathon.random.database.models.RealmParticipant;

import io.realm.RealmObject;

/**
 * Created by hackathon on 1/9/16.
 */
public class Participant implements Realmable {
    private String name;
    private String seed;

    public Participant() {
    }

    public Participant(String name, String seed) {
        this.name = name;
        this.seed = seed;
    }

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

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", seed='" + seed + '\'' +
                '}';
    }

    @Override
    public RealmObject toRealmObject() {
        RealmParticipant realmParticipant = new RealmParticipant();
        realmParticipant.setName(name);
        realmParticipant.setSeed(seed);

        return realmParticipant;
    }

    @Override
    public Realmable setDataFromRealmObject(RealmObject realmObject) {
        RealmParticipant realmParticipant = (RealmParticipant) realmObject;
        name = realmParticipant.getName();
        seed = realmParticipant.getSeed();

        return this;
    }
}
