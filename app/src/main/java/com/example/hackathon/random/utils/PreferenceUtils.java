package com.example.hackathon.random.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.hackathon.random.RandomAllApplication;

/**
 * Created by hackathon on 1/9/16.
 */
public class PreferenceUtils {
    private static PreferenceUtils sInstance;
    private SharedPreferences mSharedPreferences;
    private Context mContext;

    private PreferenceUtils() {
        mContext = RandomAllApplication.applicationContext;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public static PreferenceUtils getInstance() {
        if (sInstance == null) {
            sInstance = new PreferenceUtils();
        }
        return sInstance;
    }

    public static void reset() {
        if (sInstance != null) {
            sInstance.resetData();
        }
        sInstance = null;
    }

    public static void setInstance(PreferenceUtils instance) {
        sInstance = instance;
    }

    public void resetData() {
        mSharedPreferences.edit().clear().apply();
    }

    public void saveRandomMethod(String randomMethod) {
        mSharedPreferences.edit().putString(Constants.PREFERENCE_RANDOM_METHOD, randomMethod).apply();
    }

    public String getRandomMethod() {
        return mSharedPreferences.getString(Constants.PREFERENCE_RANDOM_METHOD, Constants.RANDOM_METHOD_PLAYERS);
    }

    public void saveCategory(String category) {
        mSharedPreferences.edit().putString(Constants.PREFERENCE_CATEGORY, category).apply();
    }

    public String getCategory() {
        return mSharedPreferences.getString(Constants.PREFERENCE_CATEGORY, Constants.CATEGORY_NONE);
    }
}
