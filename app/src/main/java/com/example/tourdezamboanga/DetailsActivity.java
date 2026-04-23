package com.example.tourdezamboanga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.button.MaterialButton;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView detailTitle = findViewById(R.id.detailTitle);
        TextView detailDesc = findViewById(R.id.detailDesc);
        TextView detailLocation = findViewById(R.id.detailLocation);
        TextView detailLandmarks = findViewById(R.id.detailLandmarks);
        TextView detailHours = findViewById(R.id.detailHours);
        TextView detailActivities = findViewById(R.id.detailActivities);
        TextView detailServices = findViewById(R.id.detailServices);
        TextView detailEstimatedCosts = findViewById(R.id.detailEstimatedCosts);
        ImageView detailImage = findViewById(R.id.detailImage);
        ImageButton btnBack = findViewById(R.id.btnBack);
        MaterialButton btnViewMap = findViewById(R.id.btnViewMap);
        NestedScrollView detailScroll = findViewById(R.id.main);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String location = intent.getStringExtra("location");
        String landmarkNotes = intent.getStringExtra("landmarkNotes");
        String openingHours = intent.getStringExtra("openingHours");
        String activities = intent.getStringExtra("activities");
        String services = intent.getStringExtra("services");
        String estimatedCosts = intent.getStringExtra("estimatedCosts");
        String mapQuery = intent.getStringExtra("mapQuery");
        int image = intent.getIntExtra("image", R.drawable.blurrycityhall);

        detailTitle.setText(name != null ? name : getString(R.string.app_name));
        detailDesc.setText(description != null ? description : "Explore one of Zamboanga's must-visit destinations.");
        detailLocation.setText(location != null ? location : "Zamboanga City");
        detailLandmarks.setText(landmarkNotes != null ? landmarkNotes : "Landmark details are coming soon.");
        detailHours.setText(openingHours != null ? openingHours : "Check local schedules before visiting");
        detailActivities.setText(activities != null ? activities : "Activities are coming soon.");
        detailServices.setText(services != null ? services : "Services are coming soon.");
        detailEstimatedCosts.setText(estimatedCosts != null ? estimatedCosts : "Estimated costs are coming soon.");
        detailImage.setImageResource(image);

        btnBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
        btnViewMap.setOnClickListener(v -> openMap(mapQuery, location, name));
        detailScroll.setOnGenericMotionListener((v, event) -> scrollWithWheelOrTrackpad(detailScroll, event));
    }

    private void openMap(String mapQuery, String location, String name) {
        String destination = mapQuery;
        if (destination == null || destination.trim().isEmpty()) {
            destination = location;
        }
        if (destination == null || destination.trim().isEmpty()) {
            destination = name != null ? name + ", Zamboanga City" : "Zamboanga City";
        }

        Uri mapsUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(destination));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, mapsUri));
        }
    }

    private boolean scrollWithWheelOrTrackpad(NestedScrollView scrollView, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_SCROLL) {
            return false;
        }

        float verticalScroll = event.getAxisValue(MotionEvent.AXIS_VSCROLL);
        if (verticalScroll == 0f) {
            return false;
        }

        scrollView.smoothScrollBy(0, Math.round(-verticalScroll * 140));
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_slide_back_in, R.anim.fade_slide_back_out);
    }
}
