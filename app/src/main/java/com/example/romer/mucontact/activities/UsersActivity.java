package com.example.romer.mucontact.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.romer.mucontact.R;
import com.example.romer.mucontact.adapters.UsersAdapter;
import com.example.romer.mucontact.models.User;
import com.example.romer.mucontact.network.NewApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class UsersActivity extends AppCompatActivity {
    List<User> users;
    private static String TAG = "MuContact";
    RecyclerView usersRecyclerView;
    UsersAdapter usersAdapter;
    RecyclerView.LayoutManager usersLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        users = new ArrayList<>();
        usersRecyclerView = (RecyclerView) findViewById(R.id.usersRecyclerView);
        usersAdapter = new UsersAdapter(users);
        usersLayoutManager = new LinearLayoutManager(this);
        usersRecyclerView.setAdapter(usersAdapter);
        usersRecyclerView.setLayoutManager(usersLayoutManager);
        updateUsers();
    }

    private void updateUsers() {
        AndroidNetworking
                .get(NewApiService.USERS_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            users = User.build(response.getJSONArray("users"));
                            Log.d(TAG, "Found Users: " + String.valueOf(users.size()));
                            usersAdapter.setUsers(users);
                            usersAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getMessage());

                    }
                });

    }
}
