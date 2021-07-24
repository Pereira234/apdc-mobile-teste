package com.example.lastapp.ui.updateUser;

import androidx.annotation.Nullable;

/**
 * Data validation state of the registration form.
 */
class UpdateUserFormState {

    @Nullable
    private Integer nameError;

    private boolean isDataValid;

    UpdateUserFormState(@Nullable Integer nameError) {
        this.nameError = nameError;
        this.isDataValid = false;
    }

    UpdateUserFormState(boolean isDataValid) {
        this.nameError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getNameError() {
        return nameError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}