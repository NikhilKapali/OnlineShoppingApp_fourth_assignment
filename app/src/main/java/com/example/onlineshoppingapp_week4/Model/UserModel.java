package com.example.onlineshoppingapp_week4.Model;

public class UserModel {

    private String userFname,userLname,username,password;

    public UserModel(String userFname, String userLname, String username, String password) {
        this.userFname = userFname;
        this.userLname = userLname;
        this.username = username;
        this.password = password;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
