package com.example.lastapp.data;

import com.example.lastapp.data.model.RegisterRequest;
import com.example.lastapp.data.model.LoginResponse;
import com.example.lastapp.data.model.LoginRequest;
import com.example.lastapp.data.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("rest/users/login")
    Call<LoginResponse> authenticateUser(@Body LoginRequest user);

    @POST("rest/users/register")
    Call<Void> registerUser(@Body RegisterRequest data); //Nao sei se o integer esta correto
}
