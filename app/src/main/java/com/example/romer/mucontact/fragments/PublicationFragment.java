package com.example.romer.mucontact.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.romer.mucontact.MuContactApp;
import com.example.romer.mucontact.R;
import com.example.romer.mucontact.adapters.PublicationAdapter;
import com.example.romer.mucontact.models.Publication;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicationFragment extends Fragment {
    private RecyclerView publicationsRecyclerView;
    private PublicationAdapter publicationsAdapter;
    private RecyclerView.LayoutManager publicationsLayoutManager;
    private List<Publication> publications;

    public PublicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publication, container, false);
        publicationsRecyclerView = (RecyclerView) view.findViewById(R.id.publicationRecyclerView);
        publications = MuContactApp.getInstance().getPublications();
        publicationsAdapter = (new PublicationAdapter()).setPublications(publications);
        publicationsLayoutManager = new LinearLayoutManager(view.getContext());
        publicationsRecyclerView.setAdapter(publicationsAdapter);
        publicationsRecyclerView.setLayoutManager(publicationsLayoutManager);
        return view;

    }

}
