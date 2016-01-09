package com.example.hackathon.random.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hackathon.random.R;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.utils.PreferenceUtils;

/**
 * Created by hackathon on 1/9/16.
 */
public class CategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void onSeedClicked(View view) {
        launchRandomizerScreen(Constants.CATEGORY_SEED);
    }

    public void onStrengthClicked(View view) {
        launchRandomizerScreen(Constants.CATEGORY_STRENGTH);
    }

    public void onNoneClicked(View view) {
        launchRandomizerScreen(Constants.CATEGORY_NONE);
    }

    private void launchRandomizerScreen(String category) {
        PreferenceUtils.getInstance().saveCategory(category);
        Intent intent = new Intent(this, RandomizerActivity.class);
        startActivity(intent);
    }
}
