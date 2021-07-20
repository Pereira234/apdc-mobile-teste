package com.example.lastapp.data;

import com.example.lastapp.data.model.RegisterRequest;
import com.example.lastapp.data.model.RegisterResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class RegisterDataSource {

    private UserService service;

    public RegisterDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goodway-320318.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(UserService.class);
    }

    public Result<Void> register(String username, String password, String confirmation, String name, String email) {

        Call<Void> userRegisterCall = service.registerUser(new RegisterRequest(username, password, confirmation, name, email));
        try {
           Response<Void> response = userRegisterCall.execute();
           if (response.isSuccessful()){
               return new Result.Success<>(response.body());
           }
            return new Result.Error(new Exception(response.errorBody().toString()));
        } catch (IOException e) {
            return new Result.Error(new IOException("Error registering!", e));
        }
    }

}