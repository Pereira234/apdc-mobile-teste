package com.example.lastapp.data.model;

public class EventDataResponse {

    private String title;
    private String description;
    private String duration;
    private String date;
    private double lat;
    private double lon;
    private String startingTime;
    private String category;

    public void EventData(String title,String startingTime,String duration,String date,String description, double lat, double lon, String category) {
        this.date = date;
        this.setDescription(description);
        this.duration = duration;
        this.title = title;
        this.setStartingTime(startingTime);
        this.setLat(lat);
        this.setLon(lon);
        this.setCategory(category);
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public String getStartingTime() {
        return startingTime;
    }
    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

}
