package com.example.tourdezamboanga;

public class Landmark {
    private final String name;
    private final String location;
    private final String description;
    private final String openingHours;
    private final String category;
    private final int image;

    public Landmark(String name, String location, String description, String openingHours, String category, int image) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.openingHours = openingHours;
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

    public String getOpeningHours() {
        return openingHours;
    }

    public String getCategory() {
        return category;
    }

    public int getImage() {
        return image;
    }
}
