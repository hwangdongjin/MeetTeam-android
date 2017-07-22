package com.example.inyoung.teamapp.dto;

import java.io.Serializable;

/**
 * Created by MYpc on 2017-06-10.
 */
@SuppressWarnings("serial")
public class UserListDTO implements Serializable{


    private String name;

    public UserListDTO(){};

    public UserListDTO (String name){

        this.name=name;
    }





    public String getName() {
        return name;
    }



}
