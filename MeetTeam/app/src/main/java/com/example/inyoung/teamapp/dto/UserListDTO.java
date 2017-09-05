package com.example.inyoung.teamapp.dto;

import java.io.Serializable;

/**
 * Created by MYpc on 2017-06-10.
 */
@SuppressWarnings("serial")
public class UserListDTO implements Serializable{
    private String name;
    private String phoneNum;
    private String photo;
    private String email;

    public UserListDTO(){};
    public UserListDTO (String name,String phoneNum, String photo, String email){
        this.name = name;
        this.phoneNum = phoneNum;
        this.photo = photo;
        this.email = email;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
