package com.example.hackathon.random.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon.random.R;
import com.example.hackathon.random.adapters.RecyclerParticipantListAdapter;
import com.example.hackathon.random.model.Participant;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.utils.PreferenceUtils;
import com.example.hackathon.random.views.CustomLinearLayoutManager;
import com.example.hackathon.random.views.DividerItemDecoration;
import com.example.hackathon.random.views.RandomizeEditDialog;
import com.example.hackathon.random.views.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class RandomizerActivity extends BaseActivity {

    private List<Participant> mParticipants;
    private RecyclerParticipantListAdapter mAdapter;
    private Spinner mMethodSpinner;
    private Spinner mCategorySpinner;
    private EditText mNameEditText;
    private EditText mSeedEditText;
    private EditText mNumberOfTeamEditText;
    private TextView mSeedTitleTextView;
    private TextView mTotalPaticipantTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);

        initUi();

        setupRandomMethods();
        setupCategories();
        setupRandomizerList();
    }

    private void initUi() {
        mMethodSpinner = (Spinner) findViewById(R.id.method_spinner);
        mCategorySpinner = (Spinner) findViewById(R.id.category_spinner);
        mNameEditText = (EditText) findViewById(R.id.randomizer_name_edit_text);
        mSeedEditText = (EditText) findViewById(R.id.randomizer_seed_edit_text);
        mNumberOfTeamEditText = (EditText) findViewById(R.id.randomizer_team_num_edit_text);
        mSeedTitleTextView = (TextView) findViewById(R.id.randomize_seed_title);
        mTotalPaticipantTextView = (TextView) findViewById(R.id.randomizer_total_participants);
    }

    private void setupRandomMethods() {
        Spinner dropdown = (Spinner) findViewById(R.id.method_spinner);
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
        mParticipants = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.randomizer_recyclerview);

        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new RecyclerParticipantListAdapter(this, mParticipants);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                RandomizeEditDialog dialog = new RandomizeEditDialog(RandomizerActivity.this,
                        mParticipants.get(position).getName(), mParticipants.get(position).getSeed(),
                        new RandomizeEditDialog.EditDialogCallback() {
                            @Override
                            public void onSaveSelected(String name, String seed) {
                                mParticipants.get(position).setName(name);
                                mParticipants.get(position).setSeed(seed);
                                updateParticipants();
                            }

                            @Override
                            public void onDeleteSelected() {
                                mParticipants.remove(position);
                                updateParticipants();
                            }
                        });

                dialog.show();
            }
        }));
    }

    private void updateParticipants() {
        mAdapter.updateDataSource(mParticipants);
        mTotalPaticipantTextView.setText(String.format(getString(R.string.total_paricipants),
                String.valueOf(mParticipants.size())));
    }

    public void onAddClicked(View view) {
        String name = mNameEditText.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            String seed = mSeedEditText.getText().toString();
            Participant participant = new Participant(name, seed);
            mParticipants.add(participant);
            updateParticipants();
        } else {
            Toast.makeText(this, R.string.error_name_empty, Toast.LENGTH_SHORT).show();
        }
    }

    public void onRandomizeClicked(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
