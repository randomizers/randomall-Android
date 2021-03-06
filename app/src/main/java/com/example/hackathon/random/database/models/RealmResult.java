package com.example.hackathon.random.database.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hackathon on 1/9/16.
 */
public class RealmResult extends RealmObject {
    public static final String RESULT_ID = "name";

    @PrimaryKey
    private String name;
    private RealmList<RealmTeam> realmTeams;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<RealmTeam> getRealmTeams() {
        return realmTeams;
    }

    public void setRealmTeams(RealmList<RealmTeam> realmTeams) {
        this.realmTeams = realmTeams;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
