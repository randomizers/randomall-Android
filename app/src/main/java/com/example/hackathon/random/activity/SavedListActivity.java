package com.example.hackathon.random.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hackathon.random.R;
import com.example.hackathon.random.adapters.RecyclerResultListAdapter;
import com.example.hackathon.random.database.helpers.RealmDatabaseHelper;
import com.example.hackathon.random.model.Result;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.views.CustomLinearLayoutManager;
import com.example.hackathon.random.views.DividerItemDecoration;
import com.example.hackathon.random.views.RecyclerItemClickListener;

import java.util.List;

/**
 * Created by hackathon on 1/10/16.
 */
public class SavedListActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        setupResultList();
    }

    private void setupResultList() {
        final List<Result> resultList = RealmDatabaseHelper.getInstance().getResults();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.saved_list_recyclerview);

        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerResultListAdapter mAdapter = new RecyclerResultListAdapter(this, resultList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                Intent intent = new Intent(SavedListActivity.this, ResultActivity.class);
                intent.putExtra(Constants.INTENT_RESULT_NAME, resultList.get(position).getName());
                intent.putExtra(Constants.INTENT_IS_SAVED, true);
                startActivity(intent);
                finish();
            }
        }));
    }

    public void onStartNewClicked(View view) {
        try {
            ActivityCompat.finishAffinity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
