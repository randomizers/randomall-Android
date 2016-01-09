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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(seed != null ? !seed.equals(that.seed) : that.seed != null);
    }
}
