package pe.com.mucontact.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import pe.com.mucontact.adapters.CraftmenAdapter;
import pe.com.mucontact.models.Craftman;
import pe.com.mucontact.network.NewApiService;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private List<Craftman> craftmen;
    private static String TAG = "MuContact";
    private RecyclerView homeRecyclerView;
    private CraftmenAdapter craftmenAdapter;
    private RecyclerView.LayoutManager craftmenLayoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeRecyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        craftmen = new ArrayList<>();
        craftmenAdapter = (new CraftmenAdapter()).setCraftmen(craftmen);
        craftmenLayoutManager = new LinearLayoutManager(view.getContext());
        homeRecyclerView.setAdapter(craftmenAdapter);
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
                            craftmen = Craftman.build(response.getJSONArray("craftmen"));
                            Log.d(TAG, "Found Craftmen: " + String.valueOf(craftmen.size()));
                            craftmenAdapter.setCraftmen(craftmen);
                            craftmenAdapter.notifyDataSetChanged();
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
