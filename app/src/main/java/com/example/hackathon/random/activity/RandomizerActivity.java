package com.example.hackathon.random.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hackathon.random.R;
import com.example.hackathon.random.utils.Constants;

/**
 * Created by hackathon on 1/9/16.
 */
public class RandomizerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);

        Spinner dropdown = (Spinner)findViewById(R.id.random_method_spinner);
        String[] items = new String[]{Constants.RANDOM_METHOD_PLAYERS, Constants.RANDOM_METHOD_TEAMS, Constants.RANDOM_METHOD_GROUPS};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }
}
