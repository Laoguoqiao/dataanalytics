package com.team11.dataanalytics.pojo;

import java.io.Serializable;


public class User implements Serializable {

    private static final long serialVersionUID = 5234785573043148908L;

    private long userId;

    private String userName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
