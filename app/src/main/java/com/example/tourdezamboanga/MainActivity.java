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
                "May vary; often listed around daytime business hours",
                bullets("Sightseeing", "Photo taking", "Nature appreciation"),
                bullets("Viewing areas", "Park access", "Nearby picnic areas", "resting spots"),
                bullets("Free"),
                CATEGORY_WILDLIFE_ECO,
                R.drawable.pasotreehouse
        ));
        allLandmarks.add(new Landmark(
                "GREAT STA. CRUZ ISLAND",
                "Sta. Cruz Islands, Basilan Strait, off Zamboanga City (via Paseo del Mar Boat Terminal)",
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
                "N.S. Valderrosa Street, Zone IV, Zamboanga City",
                "A historic government building reflecting Spanish-era architecture and local governance.",
                "Plaza Rizal",
                "Weekdays, office hours; may vary by office and holidays",
                bullets("Heritage sightseeing", "Photo taking"),
                bullets("Government services", "Nearby restaurants and transport"),
                bullets("Free"),
                CATEGORY_HISTORICAL,
                R.drawable.cityhall
        ));
        allLandmarks.add(new Landmark(
                "FORT PILAR",
                "Fort Pilar, Valderosa St., Sta Barbara, Zamboanga City",
                "A historic Spanish-era fort that also serves as a religious shrine and cultural landmark. It is one of the most iconic attractions in Zamboanga City and a must-visit for those interested in history and heritage.",
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
                "Pasonanca Park (Maria Clara Lobregat Complex), Pasonanca Road, Zamboanga City",
                "A cultural museum showcasing Zamboanga’s history focusing on its warfare and indigenous technology, tools, and instruments.",
                "Pasonanca Butterfly Garden",
                "8:00 AM – 5:00 PM, Monday to Friday",
                bullets("Museum tours", "Cultural learning", "Photo taking"),
                bullets("Guided exhibit information", "Educational displays"),
                bullets("Free"),
                CATEGORY_MUSEUMS,
                R.drawable.elmuseodezam
        ));
        allLandmarks.add(new Landmark(
                "ONCE ISLAS",
                "Barangay Panubigan, East Coast Road, Zamboanga City (jump-off point: Panubigan Port)",
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
                "Sacol Island, off the coast of Zamboanga City (accessible via Paseo del Mar or private boat arrangements)",
                "A quiet island destination known for its calm waters, natural surroundings, and relaxing beach experience. It is ideal for visitors looking for a peaceful escape away from the busy city center.",
                "Paseo del Mar (jump-off point)",
                "Day trips",
                bullets("Beach visit", "Swimming", "Sightseeing", "Relaxation"),
                bullets("Boat transfers", "Local food stalls"),
                bullets("Boat Fee: PHP 800 - PHP 1,500"),
                CATEGORY_ISLANDS,
                R.drawable.sacolisland
        ));
        allLandmarks.add(new Landmark(
                "PASEO DEL MAR",
                "N.S. Valderrosa Street, Sta. Barbara, Zamboanga City (beside Fort Pilar)",
                "A seaside promenade where visitors can enjoy sunset views, food stalls, and access to island boat trips.",
                "Fort Pilar",
                "8:00 AM – 10:00 PM, evening is the peak visiting time",
                bullets("Sunset viewing", "Food trip", "Walking", "Boat rides"),
                bullets("Restaurants", "Boat terminal", "Souvenir areas"),
                bullets("Entrance: Free", "Meals/snacks: Depends on the store or restaurant you eat at", "Boat trips: Separate cost if booking island tours"),
                CATEGORY_PARKS_NATURE,
                R.drawable.paseodelmar
        ));
        allLandmarks.add(new Landmark(
                "AZZURA BEACH RESORT",
                "Bolong Beach Area, Bolong, Zamboanga City",
                "A private beach resort known for its relaxing seaside atmosphere, resort amenities, and family-friendly environment. It is a popular choice for swimming, picnics, and short leisure getaways.",
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
                "Abong-Abong, Barangay Pasonanca/Lunzuran, Zamboanga City",
                "A scenic public park honoring Mayor Cesar Climaco. Visitors can enjoy beautiful green spaces, walking paths, and vibrant gardens, making it an ideal spot for relaxation and leisurely strolls. The park also features playgrounds and areas for picnics, attracting both locals and tourists. Its tranquil atmosphere and picturesque scenery provide an excellent backdrop for various outdoor activities.",
                "Abong-Abong Pilgrimage Site and Cruz Mayor",
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
                "A hidden natural waterfall surrounded by greenery, ideal for travelers seeking a more adventurous and less crowded destination. It is best known for its refreshing waters and peaceful natural setting.",
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
                "A quiet waterfall attraction known for its simple, natural charm and calm surroundings. It is a good spot for relaxation, swimming, and enjoying the countryside atmosphere of Zamboanga.",
                "Countryside trails and nearby farming communities",
                "Day time",
                bullets( "Swimming", "Relaxation"),
                bullets("Limited"),
                bullets("Free or PHP 20"),
                CATEGORY_WATERFALLS,
                R.drawable.nancyfalls
        ));
        allLandmarks.add(new Landmark(
                "PLAZA PERSHING",
                "Don Pablo Lorenzo Street, Zamboanga City",
                "A central city plaza that has a gazebo like structure set on a park. Plaza Pershing was named after American General John \"Blackjack\" Pershing who was then a Captain in the 15th Cavalry Regiment (and the 1st Cavalry) during the counter-insurgency of the Philippine-American War.",
                "Zamboanga City Hall and University of Zamboanga Campus",
                "Open 24 hours",
                bullets("Walking", "Relaxation"),
                bullets("Public space"),
                bullets("Free"),
                CATEGORY_HISTORICAL,
                R.drawable.plazapershing
        ));
        allLandmarks.add(new Landmark(
                "CANELAR BARTER TRADE CENTER",
                "Canelar District, Zamboanga City",
                "A busy shopping area known for imported goods, local products, and bargain finds. It is a go-to destination for visitors who want to experience local trade and buy souvenirs or affordable items.",
                "Canelar Commercial Area and downtown Zamboanga",
                "8:00 AM – 6:00 PM",
                bullets("Shopping"),
                bullets("Retail stalls"),
                bullets("Depends on purchases"),
                CATEGORY_MARKETS,
                R.drawable.canelarbartertc
        ));
        allLandmarks.add(new Landmark(
                "PASONANCA BUTTERFLY GARDEN",
                "Pasonanca Park (Maria Clara Lobregat Complex), Pasonanca Road, Zamboanga City",
                "A nature attraction inside Pasonanca Park that features butterflies and a peaceful garden environment. It is ideal for visitors who enjoy nature viewing, photography, and eco-tourism experiences.",
                "Pasonanca Park and Pasonanca Swimming Pool",
                "8:00 AM – 6:00 PM",
                bullets("Nature viewing", "Photography"),
                bullets("Guided tours"),
                bullets("PHP 20 - PHP 50"),
                CATEGORY_WILDLIFE_ECO,
                R.drawable.butterflygarden
        ));
        allLandmarks.add(new Landmark(
                "PASONANCA PARK",
                "Pasonanca Road, Zamboanga City",
                "One of the most popular nature parks in Zamboanga City, known for its lush greenery, cool atmosphere, and family-friendly outdoor spaces. It is often visited for leisure walks, picnics, and nature appreciation.",
                "Scout Limbaga and Pasonanca Swimming Pool",
                "6:00 AM – 6:00 PM",
                bullets("Nature walks", "Sightseeing"),
                bullets("Park facilities"),
                bullets("Free"),
                CATEGORY_PARKS_NATURE,
                R.drawable.pasopark
        ));
        allLandmarks.add(new Landmark(
                "ZSCMST BIRD SANCTUARY",
                "Zamboanga State College of Marine Sciences and Technology (ZSCMST), Baliwasan, Zamboanga City",
                "A protected eco-tourism site known for its bird habitat, mangrove environment, and educational value. It is a great destination for birdwatching and learning about local biodiversity and wetland conservation.",
                "ZSCMST Campus, Fort Pilar, and Paseo del Mar",
                "Daytime",
                bullets("Birdwatching"),
                bullets("Educational visits"),
                bullets("Minimal"),
                CATEGORY_WILDLIFE_ECO,
                R.drawable.birdsanctuary
        ));
        allLandmarks.add(new Landmark(
                "MERLOQUET FALLS",
                "Sitio Merloquet, Barangay Sibulao, Zamboanga City",
                "A beautiful tiered waterfall surrounded by forest, known for its refreshing waters and scenic natural setting. It is a favorite destination for trekking, swimming, and enjoying an outdoor adventure.",
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
                "Upper Calarian, Zamboanga City (along West Coast Road)",
                "A cultural destination where visitors can see the traditional weaving skills of the Yakan people and purchase handmade crafts. It is one of the best places in the city to experience local artistry and heritage.",
                "West Coast Road and nearby souvenir shops",
                "8:00 AM – 5:00 PM",
                bullets("Weaving demonstrations", "Souvenir shopping"),
                bullets("Handicrafts"),
                bullets("Free entry"),
                CATEGORY_CULTURAL,
                R.drawable.yakanweavingvillage
        ));
        allLandmarks.add(new Landmark(
                "RAINBOW MOSQUE",
                "Barangay Sta. Maria, along Tumaga Road / Veterans Avenue Extension, Zamboanga City",
                "A colorful and visually striking mosque known for its vibrant design and cultural significance. It is one of the most recognizable religious landmarks in Zamboanga City and is often admired for its unique architecture.",
                "Tumaga Road and Veterans Avenue Extension",
                "During prayer times",
                bullets("Prayer", "Sightseeing"),
                bullets("Mosque facilities"),
                bullets("Free"),
                CATEGORY_RELIGIOUS,
                R.drawable.rainbowmosque
        ));
        allLandmarks.add(new Landmark(
                "TALUKSANGAY MOSQUE",
                "Barangay Taluksangay, West Coast Road, Zamboanga City",
                "A historic mosque known as one of the oldest Islamic landmarks in the region. It reflects the long-standing Muslim heritage of Zamboanga and is an important site for cultural and religious visits.",
                "Taluksangay Fishing Village and West Coast Road",
                "During prayer times",
                bullets("Prayer", "Cultural visit"),
                bullets("Mosque facilities"),
                bullets("Free"),
                CATEGORY_RELIGIOUS,
                R.drawable.taluksangaymosque
        ));
        allLandmarks.add(new Landmark(
                "METROPOLITAN CATHEDRAL OF THE IMMACULATE CONCEPTION",
                "La Purisima Street, Zamboanga City",
                "A major Catholic landmark in Zamboanga City known for its impressive architecture and spiritual importance. It serves as a significant place of worship and a symbol of the city’s religious heritage.",
                "Ateneo de Zamboanga University and SM City Mindpro",
                "Open daily (varies with mass schedule)",
                bullets("Prayer", "Sightseeing", "Architectural appreciation"),
                bullets("Worship services", "Visitor access", "Seating areas", "Informational displays"),
                bullets("Free"),
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
