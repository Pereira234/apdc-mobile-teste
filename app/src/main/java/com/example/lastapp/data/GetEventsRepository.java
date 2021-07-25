package com.example.lastapp.data;

import com.example.lastapp.data.model.EventDataResponse;
import com.example.lastapp.data.model.GetEventNameIDResponse;
import com.example.lastapp.data.model.LoggedInUser;

import java.util.List;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class GetEventsRepository {

    List<GetEventNameIDResponse> list;

    private static volatile GetEventsRepository instance;

    private GetEventsDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore

    // private constructor : singleton access
    private GetEventsRepository(GetEventsDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GetEventsRepository getInstance(GetEventsDataSource dataSource) {
        if (instance == null) {
            instance = new GetEventsRepository(dataSource);
        }
        return instance;
    }



//    public Result<List<GetEventNameIDResponse>> getEvents(String tokenId) {
//        Result<List<GetEventNameIDResponse>> result = dataSource.getEventsNameID(tokenId);
//
//        return result;
//    }

    public Result<List<EventDataResponse>> getEvents(String tokenId) {
        Result<List<EventDataResponse>> result = dataSource.getEventsNameID(tokenId);

        return result;
    }
}