package com.abc.proquest.Model;

/**
 * Created by Taha on 17/10/2016.
 */

public class userModel {

    public String userName;
    public String password;
    public String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
