package com.example.sakib.myapplication.models;

public class Users {
    private String UserID;
    private String Name;
    private String ProviderID;
    private String Email;
    private Float Balance;

    public void setUserID(String userID) {
        this.UserID = userID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setProviderID(String idToken) {
        ProviderID = idToken;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBalance(Float balance) {
        Balance = balance;
    }

    public String getUserID() {

        return UserID;
    }

    public String getName() {
        return Name;
    }

    public String getProviderID() {
        return ProviderID;
    }

    public String getEmail() {
        return Email;
    }

    public Float getBalance() {
        return Balance;
    }
}
