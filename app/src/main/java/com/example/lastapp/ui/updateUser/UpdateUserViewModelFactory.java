package com.example.lastapp.ui.updateUser;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.lastapp.data.UpdateUserDataSource;
import com.example.lastapp.data.UpdateUserRepository;

import java.util.concurrent.Executor;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class UpdateUserViewModelFactory implements ViewModelProvider.Factory {

    private Executor executor;

    public UpdateUserViewModelFactory(Executor executor){
        this.executor = executor;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UpdateUserViewModel.class)) {
            return (T) new UpdateUserViewModel(UpdateUserRepository.getInstance(new UpdateUserDataSource()), executor);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}