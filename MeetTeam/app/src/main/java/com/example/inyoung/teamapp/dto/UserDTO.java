package com.example.inyoung.teamapp.dto;

/**
 * Created by MYpc on 2017-05-14.
 */

public class UserDTO {
    private String id;
    private String password;
    private String name;
    private String idNum;
    private String phoneNum;
    private String addr;
    private String email;
    private String photo;

    public  UserDTO(){

    }

    public UserDTO(String id,  String email, String addr, String phoneNum, String idNum, String name, String password) {
        this.id = id;
        this.email = email;
        this.addr = addr;
        this.phoneNum = phoneNum;
        this.idNum = idNum;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
