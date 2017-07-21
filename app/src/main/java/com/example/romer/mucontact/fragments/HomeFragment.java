package com.example.romer.mucontact.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.romer.mucontact.R;
import com.example.romer.mucontact.activities.RewardActivity;
import com.example.romer.mucontact.adapters.UserAdapter;
import com.example.romer.mucontact.models.User;
import com.example.romer.mucontact.network.NewApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView usersRecyclerView;
    private UserAdapter usersAdapter;
    private RecyclerView.LayoutManager usersLayoutManager;
    private List<User> users;
    private static String TAG = "MuContact";
    private Button rewardButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        usersRecyclerView = (RecyclerView) view.findViewById(R.id.usersRecyclerView);
        users = new ArrayList<>();
        usersAdapter = (new UserAdapter()).setUsers(users);
        usersLayoutManager = new LinearLayoutManager(view.getContext());
        usersRecyclerView.setAdapter(usersAdapter);
        usersRecyclerView.setLayoutManager(usersLayoutManager);
        updateUsers();
        rewardButton = (Button)view.findViewById(R.id.rewardButton);
        rewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RewardActivity.class);
                startActivity(intent);
            }
        });
        return view;
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
