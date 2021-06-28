package com.example.lastapp.data.model;

// Class feita automaticamente pelo Retrofit e e o resultante do servico login REST
public class LoginResponse {

    String userId;
    String tokenId;
    String userRole;
    long creationDate;
    long expirationDate;

    public void UserAuthenticated(String userId, String tokenId, String userRole, long creationDate, long expirationDate){
        this.userId = userId;
        this.tokenId = tokenId;
        this.userRole = userRole;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return userId;
    }

    public String getTokenID() {
        return tokenId;
    }

    public String getUserRole() {
        return userRole;
    }

    public long getCreationData() {
        return creationDate;
    }

    public long getExpirationData() {
        return expirationDate;
    }
}
