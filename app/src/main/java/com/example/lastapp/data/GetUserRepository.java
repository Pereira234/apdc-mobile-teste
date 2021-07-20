package com.example.lastapp.data;

import com.example.lastapp.data.model.GetUserResponse;
import com.example.lastapp.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class GetUserRepository {

    private static volatile GetUserRepository instance;

    private GetUserDataSource dataSource;

    // private constructor : singleton access
    private GetUserRepository(GetUserDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GetUserRepository getInstance(GetUserDataSource dataSource) {
        if (instance == null) {
            instance = new GetUserRepository(dataSource);
        }
        return instance;
    }

    public Result<GetUserResponse> getUser(String username) {

        Result<GetUserResponse> result = dataSource.getUser(username);
        return result;
    }
}