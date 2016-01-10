package com.example.hackathon.random.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hackathon.random.R;
import com.example.hackathon.random.adapters.RecyclerTeamListAdapter;
import com.example.hackathon.random.database.helpers.RealmDatabaseHelper;
import com.example.hackathon.random.database.models.RealmEditResult;
import com.example.hackathon.random.database.models.RealmResult;
import com.example.hackathon.random.model.EditResult;
import com.example.hackathon.random.model.Result;
import com.example.hackathon.random.model.Team;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.utils.Utils;
import com.example.hackathon.random.views.CustomLinearLayoutManager;
import com.example.hackathon.random.views.DividerItemDecoration;
import com.example.hackathon.random.views.SaveListDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by hackathon on 1/9/16.
 */
public class ResultActivity extends BaseActivity {

    private List<Team> mTeams;
    private String mResultName;
    private EditResult mEditResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (getIntent().hasExtra(Constants.INTENT_RESULT_NAME)) {
            mResultName = getIntent().getStringExtra(Constants.INTENT_RESULT_NAME);
        }
        mEditResult = RealmDatabaseHelper.getInstance().getEditResult(mResultName);

        initUi();
        setupRandomizerList();
    }

    private void initUi() {
        boolean isSaved = getIntent().getBooleanExtra(Constants.INTENT_IS_SAVED, false);
        findViewById(R.id.save_edit_layout).setVisibility(isSaved ? View.GONE : View.VISIBLE);
        findViewById(R.id.reuse_delete_layout).setVisibility(isSaved ? View.VISIBLE : View.GONE);

        if (mEditResult != null) {
            TextView titleTextView = (TextView) findViewById(R.id.result_title);
            if (mEditResult.getMethod().equals(Constants.RANDOM_METHOD_GROUPS)) {
                titleTextView.setText(R.string.groups);
            } else {
                titleTextView.setText(R.string.teams);
            }
        }
    }

    private void setupRandomizerList() {
        mTeams = new ArrayList<>();
        Result result = RealmDatabaseHelper.getInstance().getResult(mResultName);
        if (result != null) {
            mTeams = result.getTeams();
        } else if (mEditResult != null) {
            int teamNumber = 1;
            try {
                teamNumber = Integer.parseInt(mEditResult.getTeamNumber());
            }catch (NumberFormatException e) {
                e.printStackTrace();
            }
            mTeams = Utils.getInstance().doSeed(mEditResult.getParticipants(), teamNumber);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.result_recyclerview);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        String method = mEditResult.getMethod() == null ? Constants.RANDOM_METHOD_TEAMS : mEditResult.getMethod();
        RecyclerTeamListAdapter adapter = new RecyclerTeamListAdapter(this, mTeams, method);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    public void onSaveClicked(View view) {
        SaveListDialog dialog = new SaveListDialog(ResultActivity.this, new SaveListDialog.ListSaveDialogCallback() {
            @Override
            public void onSaveSelected(String name) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
                Result result = new Result(name, mTeams, sdf.format(calendar.getTime()));
                RealmDatabaseHelper.getInstance().copyToRealmOrUpdate(result);
                if (mEditResult != null) {
                    RealmDatabaseHelper.getInstance().deleteRealmObject(RealmEditResult.class, RealmEditResult.EDIT_RESULT_ID, mEditResult.getResultName());
                    mEditResult.setResultName(name);
                    RealmDatabaseHelper.getInstance().copyToRealmOrUpdate(mEditResult);
                }
            }
        });
        dialog.show();
    }

    public void onEditClicked(View view) {
        if (TextUtils.isEmpty(mResultName) && mEditResult != null) {
            mResultName = mEditResult.getResultName();
        }
        Intent intent = new Intent(this, RandomizerActivity.class);
        intent.putExtra(Constants.INTENT_RESULT_NAME, mResultName);
        startActivity(intent);
        finish();
    }

    public void onDeleteClicked(View view) {
        RealmDatabaseHelper.getInstance().deleteRealmObject(RealmResult.class, RealmResult.RESULT_ID, mResultName);
        try {
            ActivityCompat.finishAffinity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
