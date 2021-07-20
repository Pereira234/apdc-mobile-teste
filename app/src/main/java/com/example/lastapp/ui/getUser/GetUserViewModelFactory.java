package com.example.lastapp.ui.getUser;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.lastapp.data.GetUserDataSource;
import com.example.lastapp.data.GetUserRepository;

import java.util.concurrent.Executor;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class GetUserViewModelFactory implements ViewModelProvider.Factory {

    private Executor executor;

    public GetUserViewModelFactory(Executor executor){
        this.executor = executor;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GetUserViewModel.class)) {
            return (T) new GetUserViewModel(GetUserRepository.getInstance(new GetUserDataSource()), executor);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}