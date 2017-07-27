package pe.com.mucontact.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pe.com.mucontact.R;
import pe.com.mucontact.models.Craftman;

/**
 * Created by Franklin on 26/07/2017.
 */

public class CraftmenAdapter extends RecyclerView.Adapter<CraftmenAdapter.ViewHolder>{
    private List<Craftman> craftmen;

    public CraftmenAdapter(List<Craftman> craftmen){this.craftmen = craftmen;}

    public CraftmenAdapter(){}

    @Override
    public CraftmenAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.content_home, parent,false));
    }

    @Override
    public void onBindViewHolder(
            CraftmenAdapter.ViewHolder holder, int  position) {
        holder.nameTextView.setText(craftmen.get(position).getName());
        holder.descriptionTextView.setText(craftmen.get(position).getDescription());
        holder.phoneTextView.setText(craftmen.get(position).getPhone());
        holder.levelTextView.setText(craftmen.get(position).getLevel());
        //holder.detailsImageView.setOnClickListener(new View.OnClickListener(){
        //@Override
        //public void onClick(View v) {
          //  MuContactApp.getInstance().setCurrentReward(craftman.get(position));
           // v.getContext().startActivity(new Intent(v.getContext(), AboutCraftmanActivity.class));
        //}
        //});
    }

    public int getItemCount() {
        return craftmen.size();
    }

    public List<Craftman> getCraftmen() {
        return craftmen;
    }

    public CraftmenAdapter setCraftmen(List<Craftman> craftmen) {
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
