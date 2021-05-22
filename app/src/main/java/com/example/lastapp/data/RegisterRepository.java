package com.example.lastapp.data;

import com.example.lastapp.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class RegisterRepository {

    private static volatile RegisterRepository instance;

    private RegisterDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore

    // private constructor : singleton access
    private RegisterRepository(RegisterDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RegisterRepository getInstance(RegisterDataSource dataSource) {
        if (instance == null) {
            instance = new RegisterRepository(dataSource);
        }
        return instance;
    }


// nao sei se faz sentido

    public Result<Integer> register(String username, String password, String confirmation, String name, String email) {
        // handle register
        Result<Integer> result = dataSource.register(username, password, confirmation, name, email);
        return result;
    }
}