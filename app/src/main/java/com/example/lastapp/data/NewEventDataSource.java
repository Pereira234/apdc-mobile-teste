package com.example.lastapp.data;

import com.example.lastapp.data.model.NewEventRequest;
import com.example.lastapp.data.model.NewEventResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class NewEventDataSource {

    private UserService service;

    public NewEventDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goodway-320318.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(UserService.class);
    }

    public Result<Void> createEvent(String name, String description, String duration, String date, Double latitude, Double longitude,
                                    String startingTime, String category, String tokenId) {

        Call<Void> newEventCall = service.newEvent(new NewEventRequest(name, description, duration, date, latitude, longitude,
                startingTime, category), tokenId);
        try {
            Response<Void> response = newEventCall.execute();
            if (response.isSuccessful()){
                return new Result.Success<>(response.body());
            }
            return new Result.Error(new Exception(response.errorBody().toString()));
        } catch (IOException e) {
            return new Result.Error(new IOException("Error creating new event!", e));
        }
    }

}