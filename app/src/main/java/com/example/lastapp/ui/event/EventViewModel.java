package com.example.lastapp.ui.event;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.lastapp.data.NewEventRepository;
import com.example.lastapp.data.Result;             //update?
import com.example.lastapp.data.model.RegisterRequest; //former LoggedInUser
import com.example.lastapp.R;
import com.example.lastapp.data.model.RegisterResponse;

import java.util.concurrent.Executor;

public class EventViewModel extends ViewModel {

    private MutableLiveData<EventFormState> eventFormState = new MutableLiveData<>();
    private MutableLiveData<EventResult> eventResult = new MutableLiveData<>();
    private NewEventRepository newEventRepository;

    private final Executor executor;

    EventViewModel(NewEventRepository newEventRepository, Executor executor) {
        this.newEventRepository = newEventRepository;
        this.executor = executor;
    }

    public LiveData<EventFormState> getEventFormState() {
        return eventFormState;
    }

    //need to update!
    public LiveData<EventResult> getEventResult() { //need to update!
        return eventResult;
    }

    public void createEvent(String name, String description, String duration, String date, Double latitude, Double longitude,
                            String startingTime, String category, String tokenId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Result<Void> result = newEventRepository.createEvent(name, description, duration, date, latitude, longitude,
                        startingTime, category, tokenId);
                if (result instanceof Result.Success) {
                    eventResult.postValue(new EventResult(true));
                } else {
                    eventResult.postValue(new EventResult(R.string.event_creation_failed));
                }
            }
        });
    }


    public void eventDataChanged(String name, String description, String duration, String date, String latitude, String longitude) {
        if (!isNameValid(name)) {
            eventFormState.setValue(new EventFormState(R.string.invalid_username, null, null, null, null, null));
        } else if (!isDescriptionValid(description)) {
            eventFormState.setValue(new EventFormState(null, R.string.invalid_description, null, null, null, null));
        } else if(!isDurationValid(duration)){
            eventFormState.setValue(new EventFormState(null, null, R.string.invalid_duration, null, null, null));
        } else if(!isDateValid(date)){
            eventFormState.setValue(new EventFormState(null, null, null, R.string.invalid_date, null, null));
        } else if(!isLatitudeValid(latitude)) {
            eventFormState.setValue(new EventFormState(null, null, null, null, R.string.invalid_latitude, null));
        } else if(!isLongitudeValid(longitude)){
            eventFormState.setValue(new EventFormState(null, null, null, null, null, R.string.invalid_longitude));
        } else {
            eventFormState.setValue(new EventFormState(true));
        }
    }

    private boolean isNameValid(String name) {
        if (name == null) {
            return false;
        } else {
            return !name.trim().isEmpty();
        }
    }

    private boolean isDescriptionValid(String description) {
        if (description == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isDurationValid(String duration) {
        if (duration == null) {
            return false;
        } else {
            return !duration.trim().isEmpty();
        }
    }

    private boolean isDateValid(String date) {
        if (date == null) {
            return false;
        } else {
            return !date.trim().isEmpty();
        }
    }

    private boolean isLatitudeValid(String latitude) {
        try{
            if(latitude.isEmpty()){
                return  false;
            }else {
                Double lat = Double.parseDouble(latitude);
            }
            return true;

        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean isLongitudeValid(String longitude) {
        try{
            if(longitude.isEmpty()){
                return  false;
            }else {
                Double lon = Double.parseDouble(longitude);
            }
            return true;

        }catch (NumberFormatException e){
            return false;
        }
    }

}