package pe.com.mucontact.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.com.mucontact.R;
import pe.com.mucontact.models.Craftmen;

/**
 * Created by Franklin on 26/07/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<Craftmen> craftmen;

    public HomeAdapter(List<Craftmen> craftmen){this.craftmen = craftmen;}

    public HomeAdapter(){}

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_home, parent,false));
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int  position) {

        holder.nameTextView.setText(craftmen.get(position).getName());
        holder.descriptionTextView.setText(craftmen.get(position).getDescription());
        holder.phoneTextView.setText(craftmen.get(position).getPhone());
        holder.levelTextView.setText(craftmen.get(position).getLevel());
    }

    public int getItemCount() {
        return craftmen.size();
    }

    public List<Craftmen> getCraftmen() {
        return craftmen;
    }

    public HomeAdapter setCraftmen(List<Craftmen> craftmen) {
        this.craftmen = craftmen;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView phoneTextView;
        TextView levelTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            phoneTextView = (TextView) itemView.findViewById(R.id.phoneTextView);
            levelTextView = (TextView) itemView.findViewById(R.id.levelTextView);
        }
    }
}
