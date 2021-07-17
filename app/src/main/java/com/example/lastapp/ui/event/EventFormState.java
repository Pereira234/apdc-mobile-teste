package com.example.lastapp.ui.event;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class EventFormState {
    @Nullable
    private Integer nameError;
    @Nullable
    private Integer descriptionError;
    @Nullable
    private Integer durationError;
    @Nullable
    private Integer dateError;
    @Nullable
    private Integer latitudeError;
    @Nullable
    private Integer longitudeError;
    private boolean isDataValid;

    EventFormState(@Nullable Integer nameError, @Nullable Integer descriptionError, @Nullable Integer durationError,
                   @Nullable Integer dateError, @Nullable Integer latitudeError, @Nullable Integer longitudeError) {
        this.nameError = nameError;
        this.descriptionError = descriptionError;
        this.durationError = durationError;
        this.dateError = dateError;
        this.latitudeError = latitudeError;
        this.longitudeError = longitudeError;
        this.isDataValid = false;
    }

    EventFormState(boolean isDataValid) {
        this.nameError = null;
        this.descriptionError = null;
        this.durationError = null;
        this.dateError = null;
        this.latitudeError = null;
        this.longitudeError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getName() { return nameError; }

    @Nullable
    Integer getDescription() {
        return descriptionError;
    }

    @Nullable
    Integer getDuration() { return durationError; }

    @Nullable
    Integer getDate() { return dateError; }

    @Nullable
    Integer getLatitude() { return latitudeError; }

    @Nullable
    Integer getLongitude() { return longitudeError; }

    boolean isDataValid() {
        return isDataValid;
    }
}