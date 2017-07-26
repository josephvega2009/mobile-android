package pe.com.mucontact.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import pe.com.mucontact.adapters.HomeAdapter;
import pe.com.mucontact.models.Craftman;
import pe.com.mucontact.network.NewApiService;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    List<Craftman> craftman;
    private static String TAG = "MuContact";
    RecyclerView homeRecyclerView;
    HomeAdapter homeAdapter;
    RecyclerView.LayoutManager craftmenLayoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeRecyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        craftman = new ArrayList<>();
        homeAdapter = new HomeAdapter(craftman);
        homeRecyclerView.setAdapter(homeAdapter);
        homeRecyclerView.setLayoutManager(craftmenLayoutManager);
        updateCraftmen();
        return view;
    }
    private void updateCraftmen() {
        AndroidNetworking
                .get(NewApiService.CRAFTMAN_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            craftman = Craftman.build(response.getJSONArray("craftman"));
                            Log.d(TAG, "Found Craftman: " + String.valueOf(craftman.size()));
                            homeAdapter.setCraftman(craftman);
                            homeAdapter.notifyDataSetChanged();
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
