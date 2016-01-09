package com.example.hackathon.random.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hackathon.random.R;
import com.example.hackathon.random.adapters.RecyclerTeamListAdapter;
import com.example.hackathon.random.database.helpers.RealmDatabaseHelper;
import com.example.hackathon.random.model.Result;
import com.example.hackathon.random.model.Team;
import com.example.hackathon.random.utils.Utils;
import com.example.hackathon.random.views.CustomLinearLayoutManager;
import com.example.hackathon.random.views.DividerItemDecoration;
import com.example.hackathon.random.views.SaveListDialog;

import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class ResultActivity extends BaseActivity {

    private List<Team> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setupRandomizerList();
    }

    private void setupRandomizerList() {
        mTeams = Utils.getInstance().calculateTeams();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.result_recyclerview);

        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerTeamListAdapter mAdapter = new RecyclerTeamListAdapter(this, mTeams);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    public void onSaveClicked(View view) {
        SaveListDialog dialog = new SaveListDialog(ResultActivity.this, new SaveListDialog.ListSaveDialogCallback() {
            @Override
            public void onSaveSelected(String name) {
                Result result = new Result(name, mTeams);
                RealmDatabaseHelper.getInstance().copyToRealmOrUpdate(result);
            }
        });
        dialog.show();
    }

    public void onEditClicked(View view) {
        onBackPressed();
    }
}
