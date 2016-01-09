package com.example.hackathon.random.database.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hackathon on 1/9/16.
 */
public class RealmEditResult extends RealmObject {
    public static final String EDIT_RESULT_ID = "name";

    @PrimaryKey
    private String name;
    private String method;
    private String category;
    private RealmList<RealmParticipant> realmParticipants;
    private String teamNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RealmList<RealmParticipant> getRealmParticipants() {
        return realmParticipants;
    }

    public void setRealmParticipants(RealmList<RealmParticipant> realmParticipants) {
        this.realmParticipants = realmParticipants;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }
}
