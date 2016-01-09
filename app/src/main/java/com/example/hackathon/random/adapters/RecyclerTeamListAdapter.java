package com.example.hackathon.random.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hackathon.random.R;
import com.example.hackathon.random.model.Team;
import com.example.hackathon.random.views.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class RecyclerTeamListAdapter extends RecyclerView.Adapter<RecyclerTeamListAdapter.ViewHolder> {

    private List<Team> mDataSource;
    private Context mContext;

    public RecyclerTeamListAdapter(Context context, List<Team> dataSource) {
        mContext = context;
        if (dataSource == null) {
            mDataSource = new ArrayList<>();
        } else {
            mDataSource = dataSource;
        }
    }

    public void updateDataSource(List<Team> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.team_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(String.format(mContext.getString(R.string.team), String.valueOf(position + 1)));
        Team dataItem = mDataSource.get(position);

        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerTeamParticipantListAdapter mAdapter = new RecyclerTeamParticipantListAdapter(mContext, dataItem.getParticipants());
        holder.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.team_list_item_title);
            recyclerView = (RecyclerView) view.findViewById(R.id.team_list_participant_recyclerview);
        }
    }
}
