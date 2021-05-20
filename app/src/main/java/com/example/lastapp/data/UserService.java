package com.example.lastapp.data;

import com.example.lastapp.data.model.UserAuthenticated;
import com.example.lastapp.data.model.UserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("rest/users/login")
    Call<UserAuthenticated> authenticateUser(@Body UserCredentials user);
}
