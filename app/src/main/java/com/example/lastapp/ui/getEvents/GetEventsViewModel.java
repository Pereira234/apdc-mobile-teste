package com.example.lastapp.ui.getEvents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lastapp.R;
import com.example.lastapp.data.GetEventsRepository;
import com.example.lastapp.data.Result;
import com.example.lastapp.data.model.GetEventNameIDResponse;

import java.util.List;
import java.util.concurrent.Executor;

public class GetEventsViewModel extends ViewModel {

    private MutableLiveData<GetEventsResult> getEventsResult = new MutableLiveData<>();
    private GetEventsRepository getEventsRepository;

    private final Executor executor;

    private List<GetEventNameIDResponse> list;

    GetEventsViewModel(GetEventsRepository getEventsRepository, Executor executor) {
        this.getEventsRepository = getEventsRepository;
        this.executor = executor;
    }

    public LiveData<GetEventsResult> getGetEventsResult() {
        return getEventsResult;
    }

    public List<GetEventNameIDResponse> getEvents(String tokenId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Result<List<GetEventNameIDResponse>> result = getEventsRepository.getEvents(tokenId);
                if (result instanceof Result.Success) {
                    list = ((Result.Success<List<GetEventNameIDResponse>>) result).getData();
                    getEventsResult.postValue(new GetEventsResult(list));
                } else {
                    list = null;
                    getEventsResult.postValue(new GetEventsResult(R.string.get_events_failed));
                }
            }
        });
        return list;
    }
}