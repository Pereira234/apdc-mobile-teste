package com.example.lastapp.data;

import com.example.lastapp.data.model.LoggedInUser;
import com.example.lastapp.data.model.UserAuthenticated;
import com.example.lastapp.data.model.UserCredentials;

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

        Call<UserAuthenticated> userAuthenticatedCall = service.authenticateUser(new UserCredentials(username, password));
        try {
           Response<UserAuthenticated> response = userAuthenticatedCall.execute();
           if (response.isSuccessful()){
               UserAuthenticated ua = response.body();
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