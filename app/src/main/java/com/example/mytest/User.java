package com.example.mytest;

public class User {
    private String fullName;
    private String fullEmail;
    private String fullPassword;
    private String fullNumber;



    public User() {

    }

    public User(String fullName, String fullEmail, String fullPassword, String fullNumber) {
        this.fullName = fullName;
        this.fullEmail = fullEmail;
        this.fullPassword = fullPassword;
        this.fullNumber = fullNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullEmail() {
        return fullEmail;
    }

    public void setFullEmail(String fullEmail) {
        this.fullEmail = fullEmail;
    }

    public String getFullPassword() {
        return fullPassword;
    }

    public void setFullPassword(String fullPassword) {
        this.fullPassword = fullPassword;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }
}

