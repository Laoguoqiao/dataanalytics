package com.team11.dataanalytics.pojo;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name="user")
public class User implements Serializable{

//    private static final long serialVersionUID = 5234785573043148908L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer userId;

    @Column(name="account")
    private String userName;

    @Column(name="password")
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(Integer userId) {
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
