package com.example.lastapp.data;

import com.example.lastapp.data.model.GetUserResponse;
import com.example.lastapp.data.model.LoggedInUser;
import com.example.lastapp.data.model.LoginRequest;
import com.example.lastapp.data.model.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class GetUserDataSource {

    private UserService service;

    public GetUserDataSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://voltaic-cocoa-307910.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(UserService.class);

    }

    public Result<GetUserResponse> getUser(String username, String tokenId) {

        Call<GetUserResponse> getUserResponseCall = service.getUser(username, tokenId);
        try {
           Response<GetUserResponse> response = getUserResponseCall.execute();
           if (response.isSuccessful()){
               GetUserResponse user = response.body();
               return new Result.Success<>(user);
           }
            return new Result.Error(new Exception(response.errorBody().toString()));
        } catch (IOException e) {
            return new Result.Error(new IOException("Error fetching user data", e));
        }
    }

}