package com.sathya.user;

public class User {
    private String userName;
    private String userPhone;
    private String userMail;
    private String userId;
    private String userPassword;

    // Constructors
    public User() {
        // Default constructor
    }

    public User(String userName, String userPhone, String userMail, String userId, String userPassword) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userMail = userMail;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
