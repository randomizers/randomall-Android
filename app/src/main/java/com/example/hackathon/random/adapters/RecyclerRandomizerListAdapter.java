package com.example.hackathon.random.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hackathon.random.R;
import com.example.hackathon.random.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class RecyclerRandomizerListAdapter extends RecyclerView.Adapter<RecyclerRandomizerListAdapter.ViewHolder> {

    private List<Person> mDataSource;
    private Context mContext;

    public RecyclerRandomizerListAdapter(Context context, List<Person> dataSource) {
        mContext = context;
        if (dataSource == null) {
            mDataSource = new ArrayList<>();
        } else {
            mDataSource = dataSource;
        }
    }

    public void updateDataSource(List<Person> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.randomizer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person dataItem = mDataSource.get(position);
        holder.nameTextView.setText(dataItem.getName());
        holder.seedTextView.setText(dataItem.getSeed());
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView seedTextView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.randomizer_item_name);
            seedTextView = (TextView) view.findViewById(R.id.randomizer_item_seeds);
        }
    }
}
