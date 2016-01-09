package com.example.hackathon.random.database.helpers;

import android.util.Log;

import com.example.hackathon.random.RandomAllApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmError;

/**
 * Created by eastagile on 7/7/15.
 */
public class RealmDatabaseHelper {
    private static RealmDatabaseHelper sInstance = null;
    private Realm mRealm;

    public static RealmDatabaseHelper getInstance() {
        if (sInstance == null)
            sInstance = new RealmDatabaseHelper();
        return sInstance;
    }

    private RealmDatabaseHelper() {
        mRealm = RandomAllApplication.getRealm();
    }

    public <T> void copyToRealmOrUpdate(List<T> objectList) {
        List<RealmObject> realmObjects = new ArrayList<>();
        if (objectList != null) {
            for (T object : objectList) {
                realmObjects.add(((Realmable) object).toRealmObject());
            }
        }
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(realmObjects);
        commit();
    }

    public void copyToRealmOrUpdate(Realmable object) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(object.toRealmObject());
        commit();
    }

    public Realmable findObject(Class realmClass, String key, String value, Realmable object) {
        if (value == null) {
            return null;
        }
        RealmObject realmObject = mRealm.where(realmClass)
                .equalTo(key, value)
                .findFirst();
        if (realmObject == null) {
            return null;
        } else {
            return object.setDataFromRealmObject(realmObject);
        }
    }

    public Realmable findObject(Class realmClass, Map<String, String> conditions, Realmable object) {
        RealmQuery query = mRealm.where(realmClass);
        for (Map.Entry<String, String> entry : conditions.entrySet()) {
            query.equalTo(entry.getKey(), entry.getValue());
        }
        RealmObject realmObject = query.findFirst();
        if (realmObject == null) {
            return null;
        } else {
            return object.setDataFromRealmObject(realmObject);
        }
    }

    public RealmObject findObject(Class realmClass, String key, String value) {
        if (value == null) {
            return null;
        }
        RealmObject realmObject = mRealm.where(realmClass)
                .equalTo(key, value)
                .findFirst();
        if (realmObject == null) {
            return null;
        }
        return realmObject;
    }

    public RealmResults findObjects(Class realmClass) {
        RealmResults realmResults = mRealm.where(realmClass)
                .findAll();
        if (realmResults == null) {
            return null;
        } else {
            return realmResults;
        }
    }

    public RealmResults findObjects(Class realmClass, String key, String value) {
        RealmResults realmResults = mRealm.where(realmClass)
                .equalTo(key, value)
                .findAll();
        if (realmResults == null) {
            return null;
        } else {
            return realmResults;
        }
    }

    public RealmObject findRealmObject(Class realmClass, String key, String value) {
        return mRealm.where(realmClass)
                .equalTo(key, value)
                .findFirst();
    }

    public RealmObject findRealmObject(Class realmClass, Map<String, String> conditions) {
        RealmQuery query = mRealm.where(realmClass);
        for (Map.Entry<String, String> entry : conditions.entrySet()) {
            query.equalTo(entry.getKey(), entry.getValue());
        }
        return query.findFirst();
    }

    public void copyToRealmOrUpdate(RealmObject realmObject) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(realmObject);
        commit();
    }

    public void findAndUpdateRealmObject(Class realmClass, String key, String value, RealmUpdatable realmUpdatable) {
        try {
            mRealm.beginTransaction();
            RealmObject realmObject = mRealm.where(realmClass)
                    .equalTo(key, value)
                    .findFirst();
            if (realmObject == null) {
                realmObject = mRealm.createObject(realmClass);
            }
            realmUpdatable.onTransaction(realmObject);
            commit();
        }catch (RealmError ex) {
            Log.e("Error ", ex.getMessage());
        }
    }

    public void deleteRealmObject(Class realmClass, String key, String value) {
        RealmResults deleteObjects = findObjects(realmClass, key, value);
        mRealm.beginTransaction();
        for (int i = 0; i < deleteObjects.size(); i++) {
            deleteObjects.get(i).removeFromRealm();
        }
        commit();
    }

    private void commit() {
        try {
            mRealm.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (RealmError e) {
            e.printStackTrace();
        }
    }
}
