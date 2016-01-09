package com.example.hackathon.random.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon.random.R;
import com.example.hackathon.random.adapters.RecyclerParticipantListAdapter;
import com.example.hackathon.random.database.helpers.RealmDatabaseHelper;
import com.example.hackathon.random.model.EditResult;
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

    private static final List<String> METHOD_ITEMS = Arrays.asList(Constants.RANDOM_METHOD_PLAYERS,
            Constants.RANDOM_METHOD_TEAMS, Constants.RANDOM_METHOD_GROUPS);
    private static final List<String> CATEGORY_ITEMS = Arrays.asList(Constants.CATEGORY_SEED,
            Constants.CATEGORY_STRENGTH, Constants.CATEGORY_NONE);

    private List<Participant> mParticipants;
    private RecyclerParticipantListAdapter mAdapter;
    private Spinner mMethodSpinner;
    private Spinner mCategorySpinner;
    private EditText mNameEditText;
    private EditText mSeedEditText;
    private EditText mNumberOfTeamEditText;
    private TextView mSeedTitleTextView;
    private TextView mTotalPaticipantTextView;
    private TextView mTeamNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);

        initUi();

        setupRandomMethods();
        setupCategories();
        setupRandomizerList();

        if (getIntent().hasExtra(Constants.INTENT_RESULT_NAME)) {
            String name = getIntent().getStringExtra(Constants.INTENT_RESULT_NAME);
            EditResult editResult = RealmDatabaseHelper.getInstance().getEditResult(name);

            mMethodSpinner.setSelection(METHOD_ITEMS.indexOf(editResult.getMethod()));
            mCategorySpinner.setSelection(CATEGORY_ITEMS.indexOf(editResult.getCategory()));
            mNumberOfTeamEditText.setText(editResult.getTeamNumber());
            mParticipants = editResult.getParticipants();
            updateParticipants();
        }
    }

    private void initUi() {
        mMethodSpinner = (Spinner) findViewById(R.id.method_spinner);
        mCategorySpinner = (Spinner) findViewById(R.id.category_spinner);
        mNameEditText = (EditText) findViewById(R.id.randomizer_name_edit_text);
        mSeedEditText = (EditText) findViewById(R.id.randomizer_seed_edit_text);
        mNumberOfTeamEditText = (EditText) findViewById(R.id.randomizer_team_num_edit_text);
        mSeedTitleTextView = (TextView) findViewById(R.id.randomize_seed_title);
        mTotalPaticipantTextView = (TextView) findViewById(R.id.randomizer_total_participants);
        mTeamNumberTextView = (TextView) findViewById(R.id.team_number_textview);
    }

    private void setupRandomMethods() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, METHOD_ITEMS);
        mMethodSpinner.setAdapter(adapter);
        mMethodSpinner.setSelection(METHOD_ITEMS.indexOf(PreferenceUtils.getInstance().getRandomMethod()));
        mMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String method = parent.getItemAtPosition(position).toString();
                PreferenceUtils.getInstance().saveRandomMethod(method);
                if (method.equals(Constants.RANDOM_METHOD_PLAYERS)) {
                    mCategorySpinner.setEnabled(false);
                    mNumberOfTeamEditText.setText("1");
                    mNumberOfTeamEditText.setEnabled(false);
                } else {
                    mCategorySpinner.setEnabled(true);
                    mNumberOfTeamEditText.setEnabled(true);
                }

                if (method.equals(Constants.RANDOM_METHOD_GROUPS)) {
                    mTeamNumberTextView.setText(R.string.number_of_groups);
                } else {
                    mTeamNumberTextView.setText(R.string.number_of_teams);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupCategories() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, CATEGORY_ITEMS);
        mCategorySpinner.setAdapter(adapter);
        mCategorySpinner.setSelection(CATEGORY_ITEMS.indexOf(PreferenceUtils.getInstance().getCategory()));
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = parent.getItemAtPosition(position).toString();
                PreferenceUtils.getInstance().saveCategory(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
                List<Participant> excludedCurrentParticipants = new ArrayList<>(mParticipants);
                excludedCurrentParticipants.remove(position);
                RandomizeEditDialog dialog = new RandomizeEditDialog(RandomizerActivity.this,
                        mParticipants.get(position).getName(), mParticipants.get(position).getSeed(),
                        excludedCurrentParticipants,
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
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, R.string.error_name_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        for (Participant participant : mParticipants) {
            if (participant.getName().equals(name)) {
                Toast.makeText(this, R.string.error_name_duplicate, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String seed = mSeedEditText.getText().toString();
        Participant participant = new Participant(name, seed);
        mParticipants.add(participant);
        updateParticipants();
        mNameEditText.setText("");
        mSeedEditText.setText("");
        mNameEditText.requestFocus();
    }

    public void onRandomizeClicked(View view) {
        if (mParticipants.isEmpty()) {
            Toast.makeText(this, R.string.empty_participant, Toast.LENGTH_SHORT).show();
            return;
        }
        EditResult editResult = new EditResult(PreferenceUtils.getInstance().getRandomMethod(),
                PreferenceUtils.getInstance().getCategory(), mParticipants, mNumberOfTeamEditText.getText().toString());
        String tempName = String.valueOf(System.currentTimeMillis());
        editResult.setResultName(tempName);
        RealmDatabaseHelper.getInstance().copyToRealmOrUpdate(editResult);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(Constants.INTENT_RESULT_NAME, tempName);
        startActivity(intent);
    }
}
