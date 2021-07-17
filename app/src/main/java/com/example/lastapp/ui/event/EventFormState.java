package com.example.lastapp.ui.event;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class EventFormState {
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String duration;
    @Nullable
    private String date;
    @Nullable
    private Double latitude;
    @Nullable
    private Double longitude;
    private boolean isDataValid;

    EventFormState(@Nullable String name, @Nullable String description, @Nullable String duration,
                   @Nullable String date, @Nullable Double latitude, @Nullable Double longitude) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isDataValid = false;
    }

    EventFormState(boolean isDataValid) {
        this.name = null;
        this.description = null;
        this.duration = null;
        this.date = null;
        this.latitude = null;
        this.longitude = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    String getName() { return name; }

    @Nullable
    String getDescription() {
        return description;
    }

    @Nullable
    String getDuration() { return duration; }

    @Nullable
    String getDate() { return date; }

    @Nullable
    Double getLatitude() { return latitude; }

    @Nullable
    Double getLongitude() { return longitude; }

    boolean isDataValid() {
        return isDataValid;
    }
}