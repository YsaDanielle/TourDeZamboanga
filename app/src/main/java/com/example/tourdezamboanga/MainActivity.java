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
    private static final String CATEGORY_PARKS_NATURE = "Parks & Nature";
    private static final String CATEGORY_WATERFALLS = "Waterfalls";
    private static final String CATEGORY_MARKETS = "Markets";
    private static final String CATEGORY_MUSEUMS = "Museums";
    private static final String CATEGORY_RELIGIOUS = "Religious Sites";
    private static final String CATEGORY_CULTURAL = "Cultural & Heritage";
    private static final String CATEGORY_WILDLIFE_ECO = "Wildlife & Eco Spots";

    private final List<Landmark> allLandmarks = new ArrayList<>();
    private LandmarkAdapter adapter;
    private String selectedCategory = CATEGORY_ALL;
    private EditText searchBar;
    private MaterialButton[] categoryButtons;
    private String[] categoryValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        categoryButtons = new MaterialButton[] {
                findViewById(R.id.btnAll),
                findViewById(R.id.btnHistorical),
                findViewById(R.id.btnIslands),
                findViewById(R.id.btnParksNature),
                findViewById(R.id.btnWaterfalls),
                findViewById(R.id.btnMarkets),
                findViewById(R.id.btnMuseums),
                findViewById(R.id.btnReligious),
                findViewById(R.id.btnCultural),
                findViewById(R.id.btnWildlifeEco)
        };
        categoryValues = new String[] {
                CATEGORY_ALL,
                CATEGORY_HISTORICAL,
                CATEGORY_ISLANDS,
                CATEGORY_PARKS_NATURE,
                CATEGORY_WATERFALLS,
                CATEGORY_MARKETS,
                CATEGORY_MUSEUMS,
                CATEGORY_RELIGIOUS,
                CATEGORY_CULTURAL,
                CATEGORY_WILDLIFE_ECO
        };

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
                "Pasonanca Park (Maria Clara Lobregat Complex), Zamboanga City",
                "A peaceful heritage attraction, unique elevated wooden structure offering scenic views of the surrounding forest in Pasonanca Park.",
                "Scout Limbaga (Boys Scout)",
                "8:00 AM – 5:00 PM (May vary)",
                bullets("Sightseeing", "Photo taking", "Nature appreciation"),
                bullets("Viewing areas", "Park access", "Nearby picnic areas", "resting spots"),
                bullets("Free"),
                CATEGORY_WILDLIFE_ECO,
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
                bullets("Boat Fee: PHP 1,000.00 (good for up to 10 people, round trip)", "Environmental Fee: PHP 20.00/person", "Entrance Fee: PHP 100.00/person", "Cottage/guide rentals: Optional"),
                CATEGORY_ISLANDS,
                R.drawable.stacruzisland
        ));
        allLandmarks.add(new Landmark(
                "Zamboanga City Hall",
                "Valderrosa St., Zamboanga City",
                "A historic government building reflecting Spanish-era architecture and local governance.",
                "Plaza Rizal",
                "8:00 AM – 5:00 PM (Weekdays)",
                bullets("Heritage sightseeing", "Photo taking"),
                bullets("Government services", "Nearby restaurants and transport"),
                bullets("Free"),
                CATEGORY_HISTORICAL,
                R.drawable.cityhall
        ));
        allLandmarks.add(new Landmark(
                "Fort Pilar",
                "Fort Pilar Shrine, Zamboanga City, Zamboanga del Sur",
                "A 17th-century cultural museum showcasing Zamboanga’s history, traditions, and diverse heritage.",
                "Paseo del Mar",
                "8:00 AM – 5:00 PM",
                bullets("Prayer visit", "Museum visit", "Historical walk", "Cultural learning", "Photography"),
                bullets("Guided tours", "Nearby souvenir stalls", "Exhibits"),
                bullets("Shrine visit: Free", "Museum/donation fees: May apply", "Souvenirs/snacks: Optional"),
                CATEGORY_RELIGIOUS,
                R.drawable.fortpilar
        ));
        allLandmarks.add(new Landmark(
                "El Museo de Zamboanga",
                "219 Pasonanca Road, Pasonanca, Zamboanga City, 7000 Zamboanga del Sur",
                "A cultural museum showcasing Zamboanga’s history focusing on its warfare and indigenous technology, tools, and instruments.",
                "Pasonanca Butterfly Garden",
                "8:00 AM – 5:00 PM",
                bullets("Museum tours", "Cultural learning", "Photo taking"),
                bullets("Guided exhibit information", "Educational displays"),
                bullets("Free"),
                CATEGORY_MUSEUMS,
                R.drawable.elmuseodezam
        ));
        allLandmarks.add(new Landmark(
                "Once Islas",
                "Barangay Panubigan",
                "A protected island-hopping destination known for clear waters, scenic beaches, and quiet coastal views. A group of 11 scenic islands with white sand beaches and marine biodiversity.",
                "Panubigan Port",
                "7:00 AM - 2:00 PM",
                bullets("Island hopping", "Snorkeling", "Swimming"),
                bullets("Boat tours", "Local guides", "Cottage rentals"),
                bullets("Entrance Fee: PHP 100", "Boat/package fees: PHP 1,000+", "Cottage rentals: Optional"),
                CATEGORY_ISLANDS,
                R.drawable.onceislas
        ));
        allLandmarks.add(new Landmark(
                "Sacol Island",
                "Sacol Island, Zamboanga City",
                "A peaceful island area with coastal communities, beaches, and wide sea views.",
                "Accessible via Paseo del Mar",
                "Day trips",
                bullets("Beach visit", "Swimming", "Sightseeing", "Relaxation"),
                bullets("Boat transfers", "Local food stalls"),
                bullets("Boat Fee: PHP 800 - PHP 1,500"),
                CATEGORY_ISLANDS,
                R.drawable.sacolisland
        ));
        allLandmarks.add(new Landmark(
                "Paseo del Mar",
                "Beside Fort Pilar",
                "A seaside promenade where visitors can enjoy sunset views, food stalls, and access to island boat trips.",
                "Fort Pilar",
                "8:00 AM – 10:00 PM",
                bullets("Sunset viewing", "Food trip", "Walking", "Boat rides"),
                bullets("Restaurants", "Boat terminal", "Souvenir areas"),
                bullets("Entrance: Free", "Meals/snacks: Depends on the store or restaurant you eat at", "Boat trips: Separate cost if booking island tours"),
                CATEGORY_PARKS_NATURE,
                R.drawable.paseodelmar
        ));
        allLandmarks.add(new Landmark(
                "Azzura Beach Resort",
                "36QP+77H, Zamboanga City, Zamboanga del Sur",
                "A private beach resort with amenities and relaxing environment.",
                "Bolong Beach",
                "8:00 AM – 5:00 PM",
                bullets("Swimming", "Kayak rides", "Picnics"),
                bullets("Cottages", "Food", "Accommodations"),
                bullets("Entrance Fee: PHP 100 - PHP 300"),
                CATEGORY_PARKS_NATURE,
                R.drawable.pasotreehouse
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
        for (int i = 0; i < categoryButtons.length; i++) {
            String category = categoryValues[i];
            categoryButtons[i].setOnClickListener(v -> selectCategory(category));
        }
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
            boolean matchesCategory = matchesSelectedCategory(landmark);
            boolean matchesQuery = query.isEmpty()
                    || landmark.getName().toLowerCase().contains(query)
                    || landmark.getLocation().toLowerCase().contains(query)
                    || landmark.getCategory().toLowerCase().contains(query);

            if (matchesCategory && matchesQuery) {
                filteredLandmarks.add(landmark);
            }
        }

        adapter.submitList(filteredLandmarks);
    }

    private boolean matchesSelectedCategory(Landmark landmark) {
        String category = landmark.getCategory();
        if (CATEGORY_ALL.equals(selectedCategory)) {
            return true;
        }
        if (selectedCategory.equals(category)) {
            return true;
        }
        return CATEGORY_CULTURAL.equals(selectedCategory)
                && (CATEGORY_HISTORICAL.equals(category)
                || CATEGORY_MUSEUMS.equals(category)
                || CATEGORY_RELIGIOUS.equals(category));
    }

    private void updateCategorySelection() {
        for (int i = 0; i < categoryButtons.length; i++) {
            styleCategoryButton(categoryButtons[i], categoryValues[i].equals(selectedCategory));
        }
    }

    private void styleCategoryButton(MaterialButton button, boolean isSelected) {
        int backgroundColor = ContextCompat.getColor(this, isSelected ? R.color.vinta_red : android.R.color.white);
        int textColor = ContextCompat.getColor(this, isSelected ? android.R.color.white : R.color.text);
        button.setBackgroundTintList(ColorStateList.valueOf(backgroundColor));
        button.setTextColor(textColor);
    }
}
