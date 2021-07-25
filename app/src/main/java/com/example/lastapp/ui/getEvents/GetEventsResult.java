package com.example.lastapp.ui.getEvents;

import androidx.annotation.Nullable;

import com.example.lastapp.data.model.EventDataResponse;
import com.example.lastapp.data.model.GetEventNameIDResponse;

import java.util.List;


public class GetEventsResult {
    @Nullable
    private Boolean success;
    @Nullable
    private Integer error;
    @Nullable
    private List<EventDataResponse> list;
//    @Nullable
//    private List<GetEventNameIDResponse> list;

    GetEventsResult(@Nullable Integer error) {
        this.error = error;
    }
//
//    GetEventsResult(@Nullable List<GetEventNameIDResponse> list) {
//        this.success = true;
//        this.list = list;
//    }

    GetEventsResult(@Nullable List<EventDataResponse> list) {
        this.success = true;
        this.list = list;
    }

    @Nullable
    public Boolean getSuccess() {
        return success;
    }

    @Nullable
    public List<EventDataResponse> getList() {
        return list;
    }

//    @Nullable
//    public List<GetEventNameIDResponse> getList() {
//        return list;
//    }

    @Nullable
    public Integer getError() {
        return error;
    }
}