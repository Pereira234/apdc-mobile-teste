package com.example.lastapp.ui.getEvents;

import androidx.lifecycle.ViewModel;
import com.example.lastapp.data.GetEventsRepository;
import com.example.lastapp.data.Result;
import com.example.lastapp.data.model.GetEventNameIDResponse;
import java.util.List;
import java.util.concurrent.Executor;

public class GetEventsViewModel extends ViewModel {

    private GetEventsRepository getEventsRepository;

    private final Executor executor;

    private List<GetEventNameIDResponse> list;

    GetEventsViewModel(GetEventsRepository getEventsRepository, Executor executor) {
        this.getEventsRepository = getEventsRepository;
        this.executor = executor;
    }

    public List<GetEventNameIDResponse> getEvents(String tokenId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Result<List<GetEventNameIDResponse>> result = getEventsRepository.getEvents(tokenId);
                if (result instanceof Result.Success) {
                    list = ((Result.Success<List<GetEventNameIDResponse>>) result).getData();
                } else {
                    list = null;
                }
            }
        });
        return list;
    }
}