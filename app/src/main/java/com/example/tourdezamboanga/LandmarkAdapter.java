package com.example.tourdezamboanga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
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

        Glide.with(context).load(landmark.getImage()).into(holder.imgLandmark);

        holder.itemView.setOnClickListener(v -> {
            holder.itemView.setEnabled(false);
            v.animate()
                    .scaleX(0.97f)
                    .scaleY(0.97f)
                    .setDuration(70)
                    .withEndAction(() -> v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(120)
                            .withEndAction(() -> openDetails(landmark, holder.itemView))
                            .start())
                    .start();
        });
    }

    @Override
    public int getItemCount() {
        return landmarkList.size();
    }

    public void submitList(List<Landmark> newLandmarks) {
        List<Landmark> oldLandmarks = new ArrayList<>(landmarkList);
        landmarkList.clear();
        landmarkList.addAll(newLandmarks);

        DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldLandmarks.size();
            }

            @Override
            public int getNewListSize() {
                return landmarkList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return oldLandmarks.get(oldItemPosition)
                        .getName()
                        .equals(landmarkList.get(newItemPosition).getName());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Landmark oldItem = oldLandmarks.get(oldItemPosition);
                Landmark newItem = landmarkList.get(newItemPosition);
                return oldItem.getName().equals(newItem.getName())
                        && oldItem.getLocation().equals(newItem.getLocation())
                        && oldItem.getDescription().equals(newItem.getDescription())
                        && oldItem.getCategory().equals(newItem.getCategory())
                        && oldItem.getImage() == newItem.getImage();
            }
        }).dispatchUpdatesTo(this);
    }

    private void openDetails(Landmark landmark, View itemView) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("name", landmark.getName());
        intent.putExtra("location", landmark.getLocation());
        intent.putExtra("description", landmark.getDescription());
        intent.putExtra("landmarkNotes", landmark.getLandmarkNotes());
        intent.putExtra("openingHours", landmark.getOpeningHours());
        intent.putExtra("activities", landmark.getActivities());
        intent.putExtra("services", landmark.getServices());
        intent.putExtra("estimatedCosts", landmark.getEstimatedCosts());
        intent.putExtra("mapQuery", landmark.getName() + ", " + landmark.getLocation());
        intent.putExtra("image", landmark.getImage());
        context.startActivity(intent);

        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).overridePendingTransition(R.anim.fade_slide_in, R.anim.fade_slide_out);
        }

        itemView.setEnabled(true);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgLandmark;
        final TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLandmark = itemView.findViewById(R.id.imgLandmark);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
