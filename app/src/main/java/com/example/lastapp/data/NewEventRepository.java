package com.example.lastapp.data;

import com.example.lastapp.data.model.RegisterRequest;
import com.example.lastapp.data.model.RegisterResponse;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class NewEventRepository {

    private static volatile NewEventRepository instance;

    private NewEventDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore

    // private constructor : singleton access
    private NewEventRepository(NewEventDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static NewEventRepository getInstance(NewEventDataSource dataSource) {
        if (instance == null) {
            instance = new NewEventRepository(dataSource);
        }
        return instance;
    }


    public Result<Void> createEvent(String name, String description, String duration, String date, Double latitude, Double longitude) {
        // handle register
        Result<Void> result = dataSource.createEvent(name, description, duration, date, latitude, longitude);
        return result;
    }
}