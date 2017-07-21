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
 * Created by romer on 21/7/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<User> users;

    public UserAdapter() {
    }

    public UserAdapter(List<User> users) {
        this.users = users;
    }


    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new UserAdapter.ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.content_home, parent, false));
    }

    @Override
    public void onBindViewHolder(
            UserAdapter.ViewHolder holder, int position) {
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

    public UserAdapter setUsers(List<User> users) {
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
            usertypeTextView = (TextView) itemView.findViewById(R.id.typeUserTextView);
            emailTextView = (TextView) itemView.findViewById(R.id.emailTextView);
        }
    }
}
