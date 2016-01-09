package com.example.hackathon.random.database.helpers;

import io.realm.RealmObject;

/**
 * Created by eastagile on 7/7/15.
 */
public interface Realmable {
    RealmObject toRealmObject();

    Realmable setDataFromRealmObject(RealmObject realmObject);
}
