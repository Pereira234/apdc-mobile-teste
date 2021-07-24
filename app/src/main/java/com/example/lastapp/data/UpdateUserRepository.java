package com.example.lastapp.data;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class UpdateUserRepository {

    private static volatile UpdateUserRepository instance;

    private UpdateUserDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore

    // private constructor : singleton access
    private UpdateUserRepository(UpdateUserDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UpdateUserRepository getInstance(UpdateUserDataSource dataSource) {
        if (instance == null) {
            instance = new UpdateUserRepository(dataSource);
        }
        return instance;
    }


    public Result<Void> updateUser(String username, String tokenId, String name, String address, String zipcode, String cellphone, String description) {
        // handle register
        Result<Void> result = dataSource.updateUser(username, tokenId, name, address, zipcode, cellphone, description);
        return result;
    }
}