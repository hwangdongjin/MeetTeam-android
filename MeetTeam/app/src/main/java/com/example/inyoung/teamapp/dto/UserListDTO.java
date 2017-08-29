package com.example.inyoung.teamapp.dto;

import java.io.Serializable;

/**
 * Created by MYpc on 2017-06-10.
 */
@SuppressWarnings("serial")
public class UserListDTO implements Serializable{
    private String name;
    private String phoneNum;
    public UserListDTO(){};
    public UserListDTO (String name,String phoneNum){
        this.name=name;
        this.phoneNum=phoneNum;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
