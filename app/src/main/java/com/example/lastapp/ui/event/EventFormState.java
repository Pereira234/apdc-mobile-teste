package com.example.lastapp.ui.event;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
public class EventFormState {
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
    public Integer getNameError() { return nameError; }

    @Nullable
    public Integer getDescriptionError() {
        return descriptionError;
    }

    @Nullable
    public Integer getDurationError() { return durationError; }

    @Nullable
    public Integer getDateError() { return dateError; }

    @Nullable
    public Integer getLatitudeError() { return latitudeError; }

    @Nullable
    public Integer getLongitudeError() { return longitudeError; }

    public boolean isDataValid() {
        return isDataValid;
    }
}