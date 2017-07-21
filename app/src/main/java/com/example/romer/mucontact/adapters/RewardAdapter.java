package com.example.romer.mucontact.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.example.romer.mucontact.R;
import com.example.romer.mucontact.models.Reward;

import java.util.List;

/**
 * Created by romer on 20/7/2017.
 */

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {
    private List<Reward> rewards;

    public RewardAdapter() {
    }

    public RewardAdapter(List<Reward> rewards) {
        this.rewards = rewards;
    }


    @Override
    public RewardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.content_reward, parent, false));
    }

    @Override
    public void onBindViewHolder(
            RewardAdapter.ViewHolder holder, int position) {
        holder.nameTextView.setText(rewards.get(position).getName());
        holder.descriptionTextView.setText(rewards.get(position).getDescription());
        holder.pictureANImageView.setImageUrl(rewards.get(position).getUrl());
        holder.valueTextView.setText(rewards.get(position).getValue().toString());
    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public RewardAdapter setRewards(List<Reward> rewards) {
        this.rewards = rewards;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ANImageView pictureANImageView;
        TextView valueTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            pictureANImageView = (ANImageView) itemView.findViewById(R.id.pictureANImageView);
            valueTextView = (TextView) itemView.findViewById(R.id.valueTextView);
        }
    }
}
