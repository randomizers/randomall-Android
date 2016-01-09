package com.example.hackathon.random.activity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.hackathon.random.BuildConfig;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by hackathon on 1/9/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock keyguardLock = km.newKeyguardLock(MainActivity.class.getSimpleName());
            keyguardLock.disableKeyguard();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onMenuClicked(View view) {
        Intent intent = new Intent(this, SavedListActivity.class);
        startActivity(intent);
    }
}
