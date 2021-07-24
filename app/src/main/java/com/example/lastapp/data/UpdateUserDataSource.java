package com.example.lastapp.data;

import com.example.lastapp.data.model.LoggedInUser;
import com.example.lastapp.data.model.LoginRequest;
import com.example.lastapp.data.model.LoginResponse;
import com.example.lastapp.data.model.UpdateUserRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class UpdateUserDataSource {

    private UserService service;

    public UpdateUserDataSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goodway-320318.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(UserService.class);

    }

    public Result<Void> updateUser(String username, String tokenId, String name, String address, String zipcode, String cellphone, String description) {

        Call<Void> updateUserCall = service.updateUser(username, tokenId ,new UpdateUserRequest(username, description , "secret", "", name, "", cellphone, cellphone,
                address, "", "", zipcode, 0));
        try {
           Response<Void> response = updateUserCall.execute();
            if (response.isSuccessful()){
                return new Result.Success<>(response.body());
            }
            return new Result.Error(new Exception(response.errorBody().toString()));
        } catch (IOException e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

}