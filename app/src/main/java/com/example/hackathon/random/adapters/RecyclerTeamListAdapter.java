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
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.views.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class RecyclerTeamListAdapter extends RecyclerView.Adapter<RecyclerTeamListAdapter.ViewHolder> {

    private List<Team> mDataSource;
    private Context mContext;
    private String mMethod;

    public RecyclerTeamListAdapter(Context context, List<Team> dataSource, String method) {
        mContext = context;
        if (dataSource == null) {
            mDataSource = new ArrayList<>();
        } else {
            mDataSource = dataSource;
        }
        mMethod = method;
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
        if (mMethod.equals(Constants.RANDOM_METHOD_GROUPS)) {
            holder.titleTextView.setText(String.format(mContext.getString(R.string.group_text), String.valueOf(position + 1)));
        } else {
            holder.titleTextView.setText(String.format(mContext.getString(R.string.team_text), String.valueOf(position + 1)));
        }
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
