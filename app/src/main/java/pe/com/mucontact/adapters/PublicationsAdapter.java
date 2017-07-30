package pe.com.mucontact.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.com.mucontact.MuContactApp;
import pe.com.mucontact.R;
import pe.com.mucontact.activities.AddPublicationActivity;
import pe.com.mucontact.models.Publication;

/**
 * Created by romer on 25/7/2017.
 */

public class PublicationsAdapter extends RecyclerView.Adapter<PublicationsAdapter.ViewHolder>{
    private List<Publication> publications;

    public PublicationsAdapter() {
    }

    public PublicationsAdapter(List<Publication> publications) {
        this.publications = publications;
    }
    @Override
    public PublicationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_publication, parent,false));
    }

    @Override
    public void onBindViewHolder(
            PublicationsAdapter.ViewHolder holder, int position) {

        holder.instrumentTextView.setText(publications.get(position).getInstrument());
        holder.descriptionTextView.setText(publications.get(position).getDescription());
        holder.locationReferenceTextView.setText(publications.get(position).getContext());
        holder.publicationConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuContactApp.getInstance().setCurrentPublication(publications.get(position));
                v.getContext()
                        .startActivity(new Intent(v.getContext(),
                                AddPublicationActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public PublicationsAdapter setPublications(List<Publication> publications) {
        this.publications = publications;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView instrumentTextView;
        TextView descriptionTextView;
        TextView locationReferenceTextView;
        ConstraintLayout publicationConstraintLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            instrumentTextView = (TextView) itemView.findViewById(R.id.instrumentTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            locationReferenceTextView = (TextView) itemView.findViewById(R.id.locationReferenceTextView);
            publicationConstraintLayout = (ConstraintLayout) itemView.findViewById(R.id.publicationConstraintLayout);
        }
    }
}
