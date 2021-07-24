package com.example.lastapp.data.model;

public class NewEventRequest {
    String title;
    String description;
    String duration;
    String date;
    Double lat;
    Double lon;
    String startingTime;
    String category;


    public NewEventRequest(String name, String description, String duration, String date, Double latitude, Double longitude, String startingTime, String category) {
        this.title = name;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.lat = latitude;
        this.lon = longitude;
        this.startingTime = startingTime;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getCategory() {
        return category;
    }
}
