package com.example.lastapp.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;
    @Nullable
    private String tokenId;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success, @Nullable String tokenId) {
        this.success = success;
        this.tokenId = tokenId;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    String getTokenId() {
        return tokenId;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}