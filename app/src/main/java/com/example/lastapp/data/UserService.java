package com.example.lastapp.data;

import com.example.lastapp.data.model.EventDataResponse;
import com.example.lastapp.data.model.GetEventNameIDResponse;
import com.example.lastapp.data.model.GetUserResponse;
import com.example.lastapp.data.model.NewEventRequest;
import com.example.lastapp.data.model.RegisterRequest;
import com.example.lastapp.data.model.LoginResponse;
import com.example.lastapp.data.model.LoginRequest;
import com.example.lastapp.data.model.UpdateUserRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @POST("rest/users/login")
    Call<LoginResponse> authenticateUser(@Body LoginRequest user);

    @POST("rest/users/register")
    Call<Void> registerUser(@Body RegisterRequest data);

    @GET("rest/users/{user_id}")
    Call<GetUserResponse> getUser(@Path("user_id") String user_id, @Header("tokenId") String tokenId);

    @PUT("rest/users/{user_id}")
    Call<Void> updateUser(@Path("user_id") String user_id, @Header("tokenId") String tokenId, @Body UpdateUserRequest data);

    @POST("rest/users/create-event")
    Call<Void> newEvent(@Body NewEventRequest data, @Header("tokenId") String tokenId);

//    @GET("rest/users/events")
//    Call<List<GetEventNameIDResponse>> getEventsNameID(@Header("tokenId") String tokenId);

    @GET("rest/users/eventlist")
    Call<List<EventDataResponse>> getEventsNameID(@Header("tokenId") String tokenId);
}
