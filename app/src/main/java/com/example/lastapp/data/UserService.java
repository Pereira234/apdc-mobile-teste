package com.example.lastapp.data;

import com.example.lastapp.data.model.GetUserResponse;
import com.example.lastapp.data.model.RegisterRequest;
import com.example.lastapp.data.model.LoginResponse;
import com.example.lastapp.data.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("rest/users/login")
    Call<LoginResponse> authenticateUser(@Body LoginRequest user);

    @POST("rest/users/register")
    Call<Void> registerUser(@Body RegisterRequest data);

    @GET("rest/users/{user_id}")
    Call<GetUserResponse> getUser(@Path("user_id") String user_id);
}
