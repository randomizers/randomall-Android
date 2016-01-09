package com.example.hackathon.random.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hackathon.random.R;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.utils.PreferenceUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceUtils.getInstance().resetData();
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
