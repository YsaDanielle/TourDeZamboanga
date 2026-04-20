package com.example.tourdezamboanga;

public class Landmark {
    private final String name;
    private final String location;
    private final String description;
    private final String landmarkNotes;
    private final String openingHours;
    private final String activities;
    private final String services;
    private final String category;
    private final int image;

    public Landmark(
            String name,
            String location,
            String description,
            String landmarkNotes,
            String openingHours,
            String activities,
            String services,
            String category,
            int image
    ) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.landmarkNotes = landmarkNotes;
        this.openingHours = openingHours;
        this.activities = activities;
        this.services = services;
        this.category = category;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getLandmarkNotes() {
        return landmarkNotes;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getActivities() {
        return activities;
    }

    public String getServices() {
        return services;
    }

    public String getCategory() {
        return category;
    }

    public int getImage() {
        return image;
    }
}
