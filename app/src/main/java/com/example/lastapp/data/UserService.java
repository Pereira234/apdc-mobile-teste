package com.example.lastapp.data;

import com.example.lastapp.data.model.NewEventRequest;
import com.example.lastapp.data.model.RegisterRequest;
import com.example.lastapp.data.model.LoginResponse;
import com.example.lastapp.data.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("rest/users/login")
    Call<LoginResponse> authenticateUser(@Body LoginRequest user);

    @POST("rest/users/register")
    Call<Void> registerUser(@Body RegisterRequest data);

    @POST("rest/users/create-event")
    Call<Void> newEvent(@Body NewEventRequest data);
}
