package com.example.lastapp.data.model;

// Class feita automaticamente pelo Retrofit e e o resultante do servico getUser REST
public class GetUserResponse {

    String username;
    String description;
    String password;
    String name;
    String email;
    String profileType;
    String telephone;
    String cellphone;
    String primaryAddress;
    String secondaryAddress;
    String locale;
    String zipcode;
    long hoursWorked;
    String NIB;

    public void UserAuthenticated(String username, String description ,String password, String email, String name, String profileType, String telephone, String cellphone,
                                  String primaryAddress, String secondaryAddress, String locale, String zipcode, long hoursWorked) {
        this.username = username;
        this.description = description;
        this.password = password;
        this.email = email;
        this.name = name;
        this.profileType = profileType;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.primaryAddress = primaryAddress;
        this.secondaryAddress = secondaryAddress;
        this.locale = locale;
        this.zipcode = zipcode;
        this.hoursWorked = hoursWorked;
    }

    public String getUsername() {
        return username;
    }


    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileType() {
        return profileType;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public String getLocale() {
        return locale;
    }

    public String getZipcode() {
        return zipcode;
    }


    public String getName() {
        return name;
    }


    public long getHoursWorked() {
        return hoursWorked;
    }


    public String getNIB() {
        return NIB;
    }
}
