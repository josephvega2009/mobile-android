package pe.com.mucontact.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.com.mucontact.R;
import pe.com.mucontact.adapters.RewardsAdapter;
import pe.com.mucontact.models.Reward;
import pe.com.mucontact.network.NewApiService;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardFragment extends Fragment {
    List<Reward> rewards;
    private static String TAG = "MuContact";
    RecyclerView rewardsRecyclerView;
    RewardsAdapter rewardsAdapter;
    RecyclerView.LayoutManager rewardsLayoutManager;
    private int spanCount = 2;

    public RewardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        rewardsRecyclerView = (RecyclerView) view.findViewById(R.id.rewardsRecyclerView);
        rewards = new ArrayList<>();
        rewardsAdapter = new RewardsAdapter(rewards);

        spanCount = getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT ? 2 : 3;
        rewardsLayoutManager = new GridLayoutManager(view.getContext(), spanCount);
        rewardsRecyclerView.setAdapter(rewardsAdapter);
        rewardsRecyclerView.setLayoutManager(rewardsLayoutManager);
        updateRewards();
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        spanCount = newConfig.orientation == ORIENTATION_PORTRAIT ? 2 : 3;
        ((GridLayoutManager)rewardsLayoutManager).setSpanCount(spanCount);
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
