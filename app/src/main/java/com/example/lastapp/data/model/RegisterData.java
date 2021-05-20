package com.example.lastapp.data.model;

public class RegisterData {
    String username;
    String password;
    String confirmation;
    String name;
    String email;

    public RegisterData(String username, String password, String confirmation, String name, String email) {
        this.username = username;
        this.password = password;
        this.confirmation = confirmation;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
