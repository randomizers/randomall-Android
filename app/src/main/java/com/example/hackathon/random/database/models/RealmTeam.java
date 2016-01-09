package com.example.hackathon.random.database.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hackathon on 1/9/16.
 */
public class RealmTeam extends RealmObject {

    @PrimaryKey
    private int position;
    private RealmList<RealmParticipant> realmParticipants;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public RealmList<RealmParticipant> getRealmParticipants() {
        return realmParticipants;
    }

    public void setRealmParticipants(RealmList<RealmParticipant> realmParticipants) {
        this.realmParticipants = realmParticipants;
    }
}
