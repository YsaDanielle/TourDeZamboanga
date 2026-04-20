package com.example.tourdezamboanga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class LandmarkAdapter extends RecyclerView.Adapter<LandmarkAdapter.ViewHolder> {

    private final List<Landmark> landmarkList;
    private final Context context;

    public LandmarkAdapter(List<Landmark> landmarkList, Context context) {
        this.landmarkList = new ArrayList<>(landmarkList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Landmark landmark = landmarkList.get(position);
        holder.txtTitle.setText(landmark.getName());
        holder.txtLocation.setText(landmark.getLocation());

        Glide.with(context).load(landmark.getImage()).into(holder.imgLandmark);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", landmark.getName());
            intent.putExtra("location", landmark.getLocation());
            intent.putExtra("description", landmark.getDescription());
            intent.putExtra("openingHours", landmark.getOpeningHours());
            intent.putExtra("image", landmark.getImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return landmarkList.size();
    }

    public void submitList(List<Landmark> newLandmarks) {
        landmarkList.clear();
        landmarkList.addAll(newLandmarks);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgLandmark;
        final TextView txtTitle;
        final TextView txtLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLandmark = itemView.findViewById(R.id.imgLandmark);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtLocation = itemView.findViewById(R.id.txtLocation);
        }
    }
}
