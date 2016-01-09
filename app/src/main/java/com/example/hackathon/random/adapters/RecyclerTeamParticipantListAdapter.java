package com.example.hackathon.random.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.hackathon.random.R;
import com.example.hackathon.random.model.Participant;
import com.example.hackathon.random.views.Typewriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class RecyclerTeamParticipantListAdapter extends RecyclerView.Adapter<RecyclerTeamParticipantListAdapter.ViewHolder> {

    private List<Participant> mDataSource;
    private Context mContext;

    public RecyclerTeamParticipantListAdapter(Context context, List<Participant> dataSource) {
        mContext = context;
        if (dataSource == null) {
            mDataSource = new ArrayList<>();
        } else {
            mDataSource = dataSource;
        }
    }

    public void updateDataSource(List<Participant> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.team_participant_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Participant dataItem = mDataSource.get(position);
//        holder.nameTextView.setCharacterDelay(150);
        holder.nameTextView.animateText(dataItem.getName());
        holder.seedTextView.setText(dataItem.getSeed());
        setAnimation(holder.container, position);
    }

    int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.push_left_in);
            animation.setStartOffset(1000 * position);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View container;
        Typewriter nameTextView;
        TextView seedTextView;

        public ViewHolder(View view) {
            super(view);
            container = view.findViewById(R.id.container);
            nameTextView = (Typewriter) view.findViewById(R.id.team_participant_item_name);
            seedTextView = (TextView) view.findViewById(R.id.randomizer_item_seed);
        }
    }
}
