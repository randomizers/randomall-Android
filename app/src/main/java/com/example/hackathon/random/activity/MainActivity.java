package com.example.hackathon.random.activity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.hackathon.random.BuildConfig;
import com.example.hackathon.random.R;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.utils.PreferenceUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unlockCircleCiAvdScreen();
    }

    private void unlockCircleCiAvdScreen() {
        if (BuildConfig.DEBUG) {
            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock keyguardLock = km.newKeyguardLock(MainActivity.class.getSimpleName());
            keyguardLock.disableKeyguard();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
    }

    public void onPlayersClicked(View view) {
        PreferenceUtils.getInstance().saveRandomMethod(Constants.RANDOM_METHOD_PLAYERS);
        Intent intent = new Intent(this, RandomizerActivity.class);
        startActivity(intent);
    }

    public void onTeamsClicked(View view) {
        launchCategoryScreen(Constants.RANDOM_METHOD_TEAMS);
    }

    public void onGroupsClicked(View view) {
        launchCategoryScreen(Constants.RANDOM_METHOD_GROUPS);
    }

    private void launchCategoryScreen(String randomMethod) {
        PreferenceUtils.getInstance().saveRandomMethod(randomMethod);
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}
