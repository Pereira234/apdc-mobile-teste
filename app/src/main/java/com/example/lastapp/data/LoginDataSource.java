package com.example.lastapp.data;

import com.example.lastapp.data.model.LoggedInUser;
import com.example.lastapp.data.model.LoginResponse;
import com.example.lastapp.data.model.LoginRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private UserService service;

    public LoginDataSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://voltaic-cocoa-307910.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(UserService.class);

    }

    public Result<LoggedInUser> login(String username, String password) {

        Call<LoginResponse> userAuthenticatedCall = service.authenticateUser(new LoginRequest(username, password));
        try {
           Response<LoginResponse> response = userAuthenticatedCall.execute();
           if (response.isSuccessful()){
               LoginResponse ua = response.body();
               return new Result.Success<>(new LoggedInUser(ua.getTokenID() ,ua.getUsername()));
           }
            return new Result.Error(new Exception(response.errorBody().toString()));
        } catch (IOException e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}