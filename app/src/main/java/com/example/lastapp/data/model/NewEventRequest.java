package com.example.lastapp.data.model;

import androidx.annotation.Nullable;

public class NewEventRequest {
    String name;
    String description;
    String duration;
    String date;
    Double latitude;
    Double longitude;


    public NewEventRequest(String name, String description, String duration, String date, Double latitude, Double longitude) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getname() {
        return name;
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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
