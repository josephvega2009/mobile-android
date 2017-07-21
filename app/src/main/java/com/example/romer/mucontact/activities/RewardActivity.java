package com.example.romer.mucontact.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.romer.mucontact.R;
import com.example.romer.mucontact.adapters.RewardAdapter;
import com.example.romer.mucontact.models.Reward;
import com.example.romer.mucontact.network.NewApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RewardActivity extends AppCompatActivity {
    List<Reward> rewards;
    private static String TAG = "MuContact";
    RecyclerView rewardsRecyclerView;
    RewardAdapter rewardsAdapter;
    RecyclerView.LayoutManager rewardsLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rewards = new ArrayList<>();
        rewardsRecyclerView = (RecyclerView) findViewById(R.id.rewardsRecyclerView);
        rewardsAdapter = new RewardAdapter(rewards);
        rewardsLayoutManager = new LinearLayoutManager(this);
        rewardsRecyclerView.setAdapter(rewardsAdapter);
        rewardsRecyclerView.setLayoutManager(rewardsLayoutManager);
        updateRewards();
    }

    private void updateRewards() {
        AndroidNetworking
                .get(NewApiService.REWARD_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            rewards = Reward.build(response.getJSONArray("rewards"));
                            Log.d(TAG, "Found Rewards: " + String.valueOf(rewards.size()));
                            rewardsAdapter.setRewards(rewards);
                            rewardsAdapter.notifyDataSetChanged();
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
