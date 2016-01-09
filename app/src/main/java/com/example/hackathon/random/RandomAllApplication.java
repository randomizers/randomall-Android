package com.example.hackathon.random;

import android.app.Application;
import android.content.Context;

import com.example.hackathon.random.utils.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by EastAgile Team on 17/11/2014.
 */
public class RandomAllApplication extends Application {
    public static Context applicationContext;
    private static Realm sRealm;

    public static Realm getRealm() {
        return sRealm;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        RealmConfiguration config = new RealmConfiguration.Builder(applicationContext)
                .name(Constants.REALM_DATABASE_NAME)
                .schemaVersion(Constants.REALM_DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        sRealm = Realm.getInstance(config);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_path_open_sans_light))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
