package com.example.tourdezamboanga;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String CATEGORY_ALL = "All";
    private static final String CATEGORY_HISTORICAL = "Historical Landmarks";
    private static final String CATEGORY_ISLANDS = "Islands";
    private static final String CATEGORY_PARKS = "Parks";

    private final List<Landmark> allLandmarks = new ArrayList<>();
    private LandmarkAdapter adapter;
    private String selectedCategory = CATEGORY_ALL;
    private EditText searchBar;
    private MaterialButton btnAll;
    private MaterialButton btnHistorical;
    private MaterialButton btnIslands;
    private MaterialButton btnParks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        btnAll = findViewById(R.id.btnAll);
        btnHistorical = findViewById(R.id.btnHistorical);
        btnIslands = findViewById(R.id.btnIslands);
        btnParks = findViewById(R.id.btnParks);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadLandmarks();
        adapter = new LandmarkAdapter(allLandmarks, this);
        recyclerView.setAdapter(adapter);

        setupCategoryButtons();
        setupSearch();
        updateCategorySelection();
        filterLandmarks();
    }

    private void loadLandmarks() {
        allLandmarks.add(new Landmark(
                "Pasonanca Tree House",
                "Pasonanca Park",
                "A peaceful heritage attraction inside the park grounds, surrounded by tall trees and lush greenery.",
                "Pasonanca Natural Park",
                "8:00 AM - 5:00 PM",
                bullets("Photo taking", "Nature walk", "Sightseeing"),
                bullets("Park access", "Nearby picnic areas"),
                CATEGORY_PARKS,
                R.drawable.pasotreehouse
        ));
        allLandmarks.add(new Landmark(
                "Great Sta. Cruz Island",
                "Sta. Cruz Bank, Basilan Strait",
                "Famous for its rare pink sand beach caused by crushed red corals, one of the most unique beaches in the Philippines.",
                "Paseo del Mar (Boat Terminal)",
                "7:00 AM - 2:00 PM",
                bullets("Swimming", "Snorkeling", "Vinta riding", "Lagoon tour"),
                bullets("Cottage rentals", "Guided tours", "Boat transfers"),
                CATEGORY_ISLANDS,
                R.drawable.stacruzisland
        ));
        allLandmarks.add(new Landmark(
                "Zamboanga City Hall",
                "City Proper",
                "A landmark government building with a classic facade that reflects the city's civic and colonial history.",
                "City Hall facade",
                "8:00 AM - 5:00 PM",
                bullets("Photo taking", "Heritage sightseeing"),
                bullets("Public access around the plaza", "Nearby restaurants and transport"),
                CATEGORY_HISTORICAL,
                R.drawable.cityhall
        ));
        allLandmarks.add(new Landmark(
                "Fort Pilar",
                "N.S. Valderrosa Street",
                "A 17th-century Spanish fort and shrine that remains one of the city's most visited historical landmarks.",
                "Fort Pilar Shrine and Museum",
                "6:00 AM - 6:00 PM",
                bullets("Prayer visit", "Museum visit", "Historical walk"),
                bullets("Museum access", "Nearby souvenir stalls"),
                CATEGORY_HISTORICAL,
                R.drawable.fortpilar
        ));
        allLandmarks.add(new Landmark(
                "El Museo de Zamboanga",
                "Pasonanca",
                "A museum experience featuring local artifacts, stories, and cultural collections from the region.",
                "Cultural exhibits",
                "9:00 AM - 5:00 PM",
                bullets("Museum tour", "Cultural learning", "Photo taking"),
                bullets("Guided exhibit information", "Educational displays"),
                CATEGORY_HISTORICAL,
                R.drawable.elmuseodezam
        ));
        allLandmarks.add(new Landmark(
                "Once Islas",
                "Eleven Islands, Zamboanga City",
                "A protected island-hopping destination known for clear waters, scenic beaches, and quiet coastal views.",
                "Island hopping stops",
                "7:00 AM - 3:00 PM",
                bullets("Island hopping", "Swimming", "Snorkeling"),
                bullets("Boat transfers", "Local guides", "Cottage rentals"),
                CATEGORY_ISLANDS,
                R.drawable.onceislas
        ));
        allLandmarks.add(new Landmark(
                "Sacol Island",
                "Sacol Island, Zamboanga City",
                "A peaceful island area with coastal communities, beaches, and wide sea views.",
                "Beach areas and coastal viewpoints",
                "7:00 AM - 4:00 PM",
                bullets("Beach visit", "Swimming", "Sightseeing"),
                bullets("Boat transfers", "Local food stalls"),
                CATEGORY_ISLANDS,
                R.drawable.sacolisland
        ));
        allLandmarks.add(new Landmark(
                "Paseo del Mar",
                "Paseo del Mar, Zamboanga City",
                "A seaside promenade where visitors can enjoy sunset views, food stalls, and access to island boat trips.",
                "Boat terminal and seaside promenade",
                "6:00 AM - 10:00 PM",
                bullets("Sunset viewing", "Food trip", "Walking", "Boat departure point"),
                bullets("Restaurants", "Boat terminal", "Souvenir areas"),
                CATEGORY_PARKS,
                R.drawable.paseodelmar
        ));
    }

    private String bullets(String... items) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            if (i > 0) {
                builder.append("\n");
            }
            builder.append("• ").append(items[i]);
        }
        return builder.toString();
    }

    private void setupCategoryButtons() {
        btnAll.setOnClickListener(v -> selectCategory(CATEGORY_ALL));
        btnHistorical.setOnClickListener(v -> selectCategory(CATEGORY_HISTORICAL));
        btnIslands.setOnClickListener(v -> selectCategory(CATEGORY_ISLANDS));
        btnParks.setOnClickListener(v -> selectCategory(CATEGORY_PARKS));
    }

    private void setupSearch() {
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterLandmarks();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void selectCategory(String category) {
        selectedCategory = category;
        updateCategorySelection();
        filterLandmarks();
    }

    private void filterLandmarks() {
        String query = searchBar.getText().toString().trim().toLowerCase();
        List<Landmark> filteredLandmarks = new ArrayList<>();

        for (Landmark landmark : allLandmarks) {
            boolean matchesCategory = CATEGORY_ALL.equals(selectedCategory)
                    || selectedCategory.equals(landmark.getCategory());
            boolean matchesQuery = query.isEmpty()
                    || landmark.getName().toLowerCase().contains(query)
                    || landmark.getLocation().toLowerCase().contains(query);

            if (matchesCategory && matchesQuery) {
                filteredLandmarks.add(landmark);
            }
        }

        adapter.submitList(filteredLandmarks);
    }

    private void updateCategorySelection() {
        styleCategoryButton(btnAll, CATEGORY_ALL.equals(selectedCategory));
        styleCategoryButton(btnHistorical, CATEGORY_HISTORICAL.equals(selectedCategory));
        styleCategoryButton(btnIslands, CATEGORY_ISLANDS.equals(selectedCategory));
        styleCategoryButton(btnParks, CATEGORY_PARKS.equals(selectedCategory));
    }

    private void styleCategoryButton(MaterialButton button, boolean isSelected) {
        int backgroundColor = ContextCompat.getColor(this, isSelected ? R.color.vinta_red : android.R.color.white);
        int textColor = ContextCompat.getColor(this, isSelected ? android.R.color.white : R.color.text);
        button.setBackgroundTintList(ColorStateList.valueOf(backgroundColor));
        button.setTextColor(textColor);
    }
}
