package com.example.hackathon.random.database.helpers;

import io.realm.RealmObject;

/**
 * Created by eastagile on 7/9/15.
 */
public interface RealmUpdatable {
    void onTransaction(RealmObject realmObject);
}
