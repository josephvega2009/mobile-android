package pe.com.mucontact.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pe.com.mucontact.MuContactApp;
import pe.com.mucontact.R;
import pe.com.mucontact.activities.AboutCraftmanActivity;
import pe.com.mucontact.models.Craftman;

/**
 * Created by Franklin on 26/07/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<Craftman> craftman;

    public HomeAdapter(List<Craftman> craftman){this.craftman = craftman;}

    public HomeAdapter(){}

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_home, parent,false));
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, final int  position) {

        holder.nameTextView.setText(craftman.get(position).getName());
        holder.descriptionTextView.setText(craftman.get(position).getDescription());
        holder.phoneTextView.setText(craftman.get(position).getPhone());
        holder.levelTextView.setText(craftman.get(position).getLevel());
        //holder.detailsImageView.setOnClickListener(new View.OnClickListener(){
        //@Override
        //public void onClick(View v) {
          //  MuContactApp.getInstance().setCurrentReward(craftman.get(position));
           // v.getContext().startActivity(new Intent(v.getContext(), AboutCraftmanActivity.class));
        //}
        //});
    }

    public int getItemCount() {
        return craftman.size();
    }

    public List<Craftman> getCraftman() {
        return craftman;
    }

    public HomeAdapter setCraftman(List<Craftman> craftman) {
        this.craftman = craftman;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView phoneTextView;
        TextView levelTextView;
        ImageView detailsImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            phoneTextView = (TextView) itemView.findViewById(R.id.phoneTextView);
            levelTextView = (TextView) itemView.findViewById(R.id.levelTextView);
            detailsImageView = (ImageView) itemView.findViewById(R.id.detailsImageView);
        }
    }
}
