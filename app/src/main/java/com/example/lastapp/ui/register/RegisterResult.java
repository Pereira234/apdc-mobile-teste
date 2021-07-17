package com.example.lastapp.ui.register;

import androidx.annotation.Nullable;


/**
 * Registration result : success (user details) or error message.
 */
class RegisterResult {
    @Nullable
    private Boolean success;
    @Nullable
    private Integer error;

    RegisterResult(@Nullable Integer error) {
        this.error = error;
    }

    RegisterResult(boolean success) {
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