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
    private static final String CATEGORY_RESORTS = "Resorts";
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
                findViewById(R.id.btnResorts),
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
                CATEGORY_RESORTS,
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
                "PASONANCA TREE HOUSE",
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
                "GREAT STA. CRUZ ISLAND",
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
                "ZAMBOANGA CITY HALL",
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
                "FORT PILAR",
                "Fort Pilar, Valderosa St., Sta Barbara, Zamboanga City",
                "A 17th-century Spanish-era fort and shrine.",
                "Paseo del Mar",
                "Approximately 7:00 AM – 5:00 PM",
                bullets("Prayer visit", "Museum visit", "Sightseeing"),
                bullets("National Museum branch"),
                bullets("Free"),
                CATEGORY_RELIGIOUS,
                R.drawable.fortpilar
        ));
        allLandmarks.add(new Landmark(
                "FORT PILAR SHRINE MUSEUM COMPLEX",
                "Fort Pilar Shrine, Zamboanga City, Zamboanga del Sur",
                "A cultural museum showcasing Zamboanga’s history, traditions, and diverse heritage.",
                "Paseo del Mar",
                "8:00 AM – 5:00 PM",
                bullets("Prayer visit", "Museum visit", "Historical walk", "Cultural learning", "Photography"),
                bullets("Guided tours", "Nearby souvenir stalls", "Exhibits"),
                bullets("Shrine visit: Free", "Museum/donation fees: May apply", "Souvenirs/snacks: Optional"),
                CATEGORY_MUSEUMS,
                R.drawable.fortpilarmuseum
        ));
        allLandmarks.add(new Landmark(
                "EL MUSEO DE ZAMBOANGA",
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
                "ONCE ISLAS",
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
                "SACOL ISLAND",
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
                "PASEO DEL MAR",
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
                "AZZURA BEACH RESORT",
                "36QP+77H, Zamboanga City, Zamboanga del Sur",
                "A private beach resort with amenities and relaxing environment.",
                "Bolong Beach",
                "8:00 AM – 5:00 PM",
                bullets("Swimming", "Kayak rides", "Picnics"),
                bullets("Cottages", "Food", "Accommodations"),
                bullets("Entrance Fee: PHP 100 - PHP 300"),
                CATEGORY_RESORTS,
                R.drawable.azzura
        ));
        allLandmarks.add(new Landmark(
                "CESAR CLIMACO FREEDOM PARK",
                "X37G+RF7 Cesar Climaco Freedom Park, Zamboanga City, 7000 Zamboanga del Sur",
                "A scenic public park honoring Mayor Cesar Climaco. Visitors can enjoy beautiful green spaces, walking paths, and vibrant gardens, making it an ideal spot for relaxation and leisurely strolls. The park also features playgrounds and areas for picnics, attracting both locals and tourists. Its tranquil atmosphere and picturesque scenery provide an excellent backdrop for various outdoor activities.",
                "Helmet Tomb and Cruz Mayor",
                "Open 24 hours",
                bullets("Walking", "Relaxing", "Sightseeing"),
                bullets("Benches", "Open space"),
                bullets("Free"),
                CATEGORY_PARKS_NATURE,
                R.drawable.climacofp
        ));
        allLandmarks.add(new Landmark(
                "TAGBILAT FALLS",
                "Malagandis, Titay, Zamboanga Sibugay",
                "A hidden natural waterfall ideal for adventure seekers.",
                "Forest trails",
                "Day time",
                bullets("Trekking", "Swimming"),
                bullets("Minimal"),
                bullets("Free or PHP 20"),
                CATEGORY_WATERFALLS,
                R.drawable.tagbilatfalls
        ));
        allLandmarks.add(new Landmark(
                "NANCY FALLS",
                "Km27, Upper La Paz, Zamboanga City",
                "A quiet waterfall destination with natural surroundings.",
                "Local trails",
                "Day time",
                bullets( "Swimming", "Relaxation"),
                bullets("Limited"),
                bullets("Free or PHP 20"),
                CATEGORY_WATERFALLS,
                R.drawable.nancyfalls
        ));
        allLandmarks.add(new Landmark(
                "PLAZA PERSHING",
                "Don Pablo Lorenzo St, Zamboanga City",
                "A central city plaza that has a gazebo like structure set on a park. Plaza Pershing was named after American General John \"Blackjack\" Pershing who was then a Captain in the 15th Cavalry Regiment (and the 1st Cavalry) during the counter-insurgency of the Philippine-American War.",
                "University of Zamboanga Campus",
                "Open 24 hours",
                bullets("Walking", "Relaxation"),
                bullets("Public space"),
                bullets("Free"),
                CATEGORY_PARKS_NATURE,
                R.drawable.plazapershing
        ));
        allLandmarks.add(new Landmark(
                "CANELAR BARTER TRADE CENTER",
                "Canelar District",
                "Known as an imported goods market and trade area.",
                "Canelar commercial area",
                "8:00 AM – 6:00 PM",
                bullets("Shopping"),
                bullets("Retail stalls"),
                bullets("Depends on purchases"),
                CATEGORY_RELIGIOUS,
                R.drawable.canelarbartertc
        ));
        allLandmarks.add(new Landmark(
                "PASONANCA BUTTERFLY GARDEN",
                "Pasonanca Park",
                "Garden showcasing various butterfly species and nature.",
                "Antonio's Mercato",
                "8:00 AM – 6:00 PM",
                bullets("Nature viewing", "Photography"),
                bullets("Guided tours"),
                bullets("PHP 20 - PHP 50"),
                CATEGORY_PARKS_NATURE,
                R.drawable.butterflygarden
        ));
        allLandmarks.add(new Landmark(
                "PASONANCA PARK",
                "Pasonanca District",
                "",
                "Antonio's Mercato",
                "6:00 AM – 6:00 PM",
                bullets("Nature walks", "Sightseeing"),
                bullets("Park facilities"),
                bullets("Free"),
                CATEGORY_PARKS_NATURE,
                R.drawable.pasopark
        ));
        allLandmarks.add(new Landmark(
                "ZSCMST BIRD SANCTUARY",
                "ZSCMST campus area",
                "",
                "University Campus",
                "Daytime",
                bullets("Birdwatching"),
                bullets("Educational visits"),
                bullets("Minimal"),
                CATEGORY_PARKS_NATURE,
                R.drawable.birdsanctuary
        ));
        allLandmarks.add(new Landmark(
                "MERLOQUET FALLS",
                "Barangay Sibulao",
                "",
                "Countryside road",
                "Daytime",
                bullets("Swimming", "Trekking"),
                bullets("Cottages"),
                bullets("PHP 20 - PHP 50"),
                CATEGORY_WATERFALLS,
                R.drawable.merloquetfalls
        ));
        allLandmarks.add(new Landmark(
                "YAKAN WEAVING VILLAGE",
                "Upper Calarian",
                "",
                "Coastal tourism corridor",
                "8:00 AM – 5:00 PM",
                bullets("Weaving demonstrations", "Souvenir shopping"),
                bullets("Handicrafts"),
                bullets("Free entry"),
                CATEGORY_WATERFALLS,
                R.drawable.yakanweavingvillage
        ));
        allLandmarks.add(new Landmark(
                "RAINBOW MOSQUE",
                "Barangay Sibulao",
                "",
                "Countryside road",
                "Daytime",
                bullets("Swimming", "Trekking"),
                bullets("Cottages"),
                bullets("PHP 20 - PHP 50"),
                CATEGORY_RELIGIOUS,
                R.drawable.rainbowmosque
        ));
        allLandmarks.add(new Landmark(
                "TALUKSANGAY MOSQUE",
                "Barangay Sibulao",
                "",
                "Countryside road",
                "Daytime",
                bullets("Swimming", "Trekking"),
                bullets("Cottages"),
                bullets("PHP 20 - PHP 50"),
                CATEGORY_RELIGIOUS,
                R.drawable.taluksangaymosque
        ));
        allLandmarks.add(new Landmark(
                "METROPOLITAN CATHEDRAL OF THE IMMACULATE CONCEPTION",
                "Barangay Sibulao",
                "",
                "Countryside road",
                "Daytime",
                bullets("Swimming", "Trekking"),
                bullets("Cottages"),
                bullets("PHP 20 - PHP 50"),
                CATEGORY_RELIGIOUS,
                R.drawable.metrocathedral
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
