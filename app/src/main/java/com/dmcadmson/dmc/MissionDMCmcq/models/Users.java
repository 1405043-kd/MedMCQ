package com.dmcadmson.dmc.MissionDMCmcq.models;

public class Users {
    private String Provider_Name;
    private String UserID;
    private String Name;
    private String Email;
    private Float Balance;

    public Users(){

    }




    public void setUserID(String userID) {
        this.UserID = userID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setProviderID(String idToken) {
        Provider_Name = idToken;
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
        return Provider_Name;
    }

    public String getEmail() {
        return Email;
    }

    public Float getBalance() {
        return Balance;
    }
}
