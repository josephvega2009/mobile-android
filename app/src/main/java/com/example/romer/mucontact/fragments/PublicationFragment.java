package com.example.romer.mucontact.fragments;


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
import com.example.romer.mucontact.R;
import com.example.romer.mucontact.adapters.PublicationAdapter;
import com.example.romer.mucontact.models.Publication;
import com.example.romer.mucontact.models.User;
import com.example.romer.mucontact.network.NewApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicationFragment extends Fragment {
    private RecyclerView publicationsRecyclerView;
    private PublicationAdapter publicationsAdapter;
    private RecyclerView.LayoutManager publicationsLayoutManager;
    private List<Publication> publications;
    private static String TAG = "MuContact";
    private User user;

    public PublicationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publication, container, false);
        publicationsRecyclerView = (RecyclerView) view.findViewById(R.id.publicationRecyclerView);
        publications = new ArrayList<>();
        publicationsAdapter = (new PublicationAdapter()).setPublications(publications);
        publicationsLayoutManager = new LinearLayoutManager(view.getContext());
        publicationsRecyclerView.setAdapter(publicationsAdapter);
        publicationsRecyclerView.setLayoutManager(publicationsLayoutManager);
        updatePublications();
        return view;
    }

    private void updatePublications() {
        AndroidNetworking
                .get(NewApiService.PUBLICATION_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            publications = Publication.build(response.getJSONArray("publications"), user);
                            Log.d(TAG, "Found Publications: " + String.valueOf(publications.size()));
                            publicationsAdapter.setPublications(publications);
                            publicationsAdapter.notifyDataSetChanged();
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
