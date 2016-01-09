package com.example.hackathon.random.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hackathon.random.R;
import com.example.hackathon.random.adapters.RecyclerRandomizerListAdapter;
import com.example.hackathon.random.model.Person;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.utils.PreferenceUtils;
import com.example.hackathon.random.views.CustomLinearLayoutManager;
import com.example.hackathon.random.views.DividerItemDecoration;
import com.example.hackathon.random.views.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class RandomizerActivity extends BaseActivity {

    private RecyclerRandomizerListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);

        setupRandomMethods();
        setupCategories();
        setupRandomizerList();
    }

    private void setupRandomMethods() {
        Spinner dropdown = (Spinner) findViewById(R.id.random_method_spinner);
        List<String> items = Arrays.asList(Constants.RANDOM_METHOD_PLAYERS, Constants.RANDOM_METHOD_TEAMS, Constants.RANDOM_METHOD_GROUPS);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(items.indexOf(PreferenceUtils.getInstance().getRandomMethod()));
    }

    private void setupCategories() {
        Spinner dropdown = (Spinner) findViewById(R.id.category_spinner);
        List<String> items = Arrays.asList(Constants.CATEGORY_SEED, Constants.CATEGORY_STRENGTH, Constants.CATEGORY_NONE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(items.indexOf(PreferenceUtils.getInstance().getCategory()));
    }

    private void setupRandomizerList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.randomizer_recyclerview);

        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new RecyclerRandomizerListAdapter(this, Arrays.asList(new Person("a", "1"), new Person("b", "2"), new Person("c", "3")
                , new Person("c", "3")
                , new Person("c", "3")
                , new Person("c", "3"), new Person("c", "3"), new Person("c", "3"), new Person("c", "3"), new Person("c", "3"), new Person("c", "3")
        ,new Person("a", "1")));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }));

    }

    public void onRandomizeClicked(View view) {

    }
}
