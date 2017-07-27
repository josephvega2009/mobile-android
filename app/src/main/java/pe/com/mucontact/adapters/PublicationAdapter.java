package pe.com.mucontact.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.com.mucontact.R;
import pe.com.mucontact.models.Publication;

/**
 * Created by romer on 25/7/2017.
 */

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder>{
    private List<Publication> publications;

    public PublicationAdapter() {
    }

    public PublicationAdapter(List<Publication> publications) {
        this.publications = publications;
    }
    @Override
    public PublicationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_publication, parent,false));
    }

    @Override
    public void onBindViewHolder(
            PublicationAdapter.ViewHolder holder, int position) {

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
