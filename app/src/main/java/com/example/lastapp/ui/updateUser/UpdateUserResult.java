package com.example.lastapp.ui.updateUser;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class UpdateUserResult {
    @Nullable
    private Boolean success;
    @Nullable
    private Integer error;

    UpdateUserResult(@Nullable Integer error) {
        this.error = error;
    }

    UpdateUserResult(boolean success) {
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