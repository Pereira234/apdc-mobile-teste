package com.example.lastapp.ui.getEvents;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.lastapp.data.GetEventsDataSource;
import com.example.lastapp.data.GetEventsRepository;
import com.example.lastapp.data.LoginDataSource;
import com.example.lastapp.data.LoginRepository;
import com.example.lastapp.ui.login.LoginViewModel;

import java.util.concurrent.Executor;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class GetEventsViewModelFactory implements ViewModelProvider.Factory {

    private Executor executor;

    public GetEventsViewModelFactory(Executor executor){
        this.executor = executor;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GetEventsViewModel.class)) {
            return (T) new GetEventsViewModel(GetEventsRepository.getInstance(new GetEventsDataSource()), executor);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}