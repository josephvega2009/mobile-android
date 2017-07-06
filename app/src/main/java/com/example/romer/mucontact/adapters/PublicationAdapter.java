package com.example.romer.mucontact.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romer.mucontact.R;
import com.example.romer.mucontact.models.Publication;

import java.util.List;

/**
 * Created by romer on 6/7/2017.
 */

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder>{
    private List<Publication> publications;
    @Override
    public PublicationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_publication, parent,false));
    }

    @Override
    public void onBindViewHolder(PublicationAdapter.ViewHolder holder, int position) {

        holder.instrumentTextView.setText(publications.get(position).getInstrument());
        holder.craftmenTextView.setText(publications.get(position).getCraftmen());
        holder.descriptionTextView.setText(publications.get(position).getDescription());
        holder.locationReferenceTextView.setText(publications.get(position).getContext());
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public PublicationAdapter setPublications(List<Publication> publications) {
        this.publications = publications;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView instrumentTextView;
        TextView craftmenTextView;
        TextView descriptionTextView;
        TextView locationReferenceTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            instrumentTextView = (TextView) itemView.findViewById(R.id.instrumentTextView);
            craftmenTextView = (TextView) itemView.findViewById(R.id.craftmenTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            locationReferenceTextView = (TextView) itemView.findViewById(R.id.locationReferenceTextView);
        }
    }

}
