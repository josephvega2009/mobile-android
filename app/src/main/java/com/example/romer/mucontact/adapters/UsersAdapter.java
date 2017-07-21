package com.example.romer.mucontact.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romer.mucontact.R;
import com.example.romer.mucontact.models.User;

import java.util.List;

/**
 * Created by INTEL on 20/07/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{
    private List<User> users;

    public UsersAdapter() {
    }

    public UsersAdapter(List<User> users) {
        this.users = users;
    }


    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new UsersAdapter.ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.content_user, parent, false));
    }

    @Override
    public void onBindViewHolder(
            UsersAdapter.ViewHolder holder, int position) {
        holder.displaynameTextView.setText(users.get(position).getDisplayName());
        holder.usertypeTextView.setText(users.get(position).getUserType());
        holder.emailTextView.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {return users.size();
    }

    public List<User> getUsers() {
        return users;
    }

    public UsersAdapter setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView displaynameTextView;
        TextView usertypeTextView;
        TextView emailTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            displaynameTextView = (TextView) itemView.findViewById(R.id.displaynameTextView);
            usertypeTextView = (TextView) itemView.findViewById(R.id.usertypeTextView);
            emailTextView = (TextView) itemView.findViewById(R.id.emailTextView);
        }
    }
}