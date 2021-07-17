package com.example.lastapp.ui.event;

import androidx.annotation.Nullable;


class EventResult {
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
    Boolean getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}