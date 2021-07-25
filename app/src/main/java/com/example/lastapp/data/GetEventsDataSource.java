package com.example.lastapp.data;

import com.example.lastapp.data.model.EventDataResponse;
import com.example.lastapp.data.model.GetEventNameIDResponse;
import com.example.lastapp.data.model.LoggedInUser;
import com.example.lastapp.data.model.LoginRequest;
import com.example.lastapp.data.model.LoginResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class GetEventsDataSource {

    private UserService service;

    public GetEventsDataSource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goodway-320318.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(UserService.class);

    }

//    public Result<List<GetEventNameIDResponse>> getEventsNameID(String tokenId) {
//
//        Call<List<GetEventNameIDResponse>> getEventsCall = service.getEventsNameID(tokenId);
//        try {
//           Response<List<GetEventNameIDResponse>> response = getEventsCall.execute();
//           if (response.isSuccessful()){
//               return new Result.Success<>(response.body());
//           }
//            return new Result.Error(new Exception(response.errorBody().toString()));
//        } catch (IOException e) {
//            return new Result.Error(new IOException("Error getting events", e));
//        }
//    }

    public Result<List<EventDataResponse>> getEventsNameID(String tokenId) {

        Call<List<EventDataResponse>> getEventsCall = service.getEventsNameID(tokenId);
        try {
           Response<List<EventDataResponse>> response = getEventsCall.execute();
           if (response.isSuccessful()){
               return new Result.Success<>(response.body());
           }
            return new Result.Error(new Exception(response.errorBody().toString()));
        } catch (IOException e) {
            return new Result.Error(new IOException("Error getting events", e));
        }
    }

}