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

    private final List<Landmark> allLandmarks = new ArrayList<>();
    private LandmarkAdapter adapter;
    private String selectedCategory = CATEGORY_ALL;
    private EditText searchBar;
    private MaterialButton btnAll;
    private MaterialButton btnHistorical;
    private MaterialButton btnIslands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        btnAll = findViewById(R.id.btnAll);
        btnHistorical = findViewById(R.id.btnHistorical);
        btnIslands = findViewById(R.id.btnIslands);

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
                "8:00 AM - 5:00 PM",
                CATEGORY_HISTORICAL,
                R.drawable.blurrycityhall
        ));
        allLandmarks.add(new Landmark(
                "Great Sta. Cruz Island",
                "Sta. Cruz Island",
                "Known for its pink coralline sandbar and calm waters, this island is one of Zamboanga's most iconic destinations.",
                "7:00 AM - 2:00 PM",
                CATEGORY_ISLANDS,
                R.drawable.blurrycityhall
        ));
        allLandmarks.add(new Landmark(
                "Zamboanga City Hall",
                "City Proper",
                "A landmark government building with a classic facade that reflects the city's civic and colonial history.",
                "8:00 AM - 5:00 PM",
                CATEGORY_HISTORICAL,
                R.drawable.blurrycityhall
        ));
        allLandmarks.add(new Landmark(
                "Fort Pilar",
                "N.S. Valderrosa Street",
                "A 17th-century Spanish fort and shrine that remains one of the city's most visited historical landmarks.",
                "6:00 AM - 6:00 PM",
                CATEGORY_HISTORICAL,
                R.drawable.blurrycityhall
        ));
        allLandmarks.add(new Landmark(
                "El Museo de Zamboanga",
                "Pasonanca",
                "A museum experience featuring local artifacts, stories, and cultural collections from the region.",
                "9:00 AM - 5:00 PM",
                CATEGORY_HISTORICAL,
                R.drawable.blurrycityhall
        ));
    }

    private void setupCategoryButtons() {
        btnAll.setOnClickListener(v -> selectCategory(CATEGORY_ALL));
        btnHistorical.setOnClickListener(v -> selectCategory(CATEGORY_HISTORICAL));
        btnIslands.setOnClickListener(v -> selectCategory(CATEGORY_ISLANDS));
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
    }

    private void styleCategoryButton(MaterialButton button, boolean isSelected) {
        int backgroundColor = ContextCompat.getColor(this, isSelected ? R.color.vinta_red : android.R.color.white);
        int textColor = ContextCompat.getColor(this, isSelected ? android.R.color.white : R.color.text);
        button.setBackgroundTintList(ColorStateList.valueOf(backgroundColor));
        button.setTextColor(textColor);
    }
}
