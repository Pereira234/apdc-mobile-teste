package com.example.lastapp.ui.event;

import androidx.annotation.Nullable;


public class EventResult {
    @Nullable
    private Boolean success;
    @Nullable
    private Integer error;

    EventResult(@Nullable Integer error) {
        this.error = error;
    }

    EventResult(boolean success) {
        this.success = success;
    }

    @Nullable
    public Boolean getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}