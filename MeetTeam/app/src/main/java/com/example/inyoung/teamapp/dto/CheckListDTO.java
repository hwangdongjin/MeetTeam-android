package com.example.inyoung.teamapp.dto;

/**
 * Created by MYpc on 2017-04-10.
 */

public class CheckListDTO {


    private String checkRoom_Name;

    public CheckListDTO(){

    }


    public CheckListDTO(String checkRoom_Name) {

        this.checkRoom_Name = checkRoom_Name;

    }
    public void setCheckRoom_Name(String checkRoom_Name) {
        this.checkRoom_Name = checkRoom_Name;
    }

    public String getCheck_RoomName() {

        return checkRoom_Name;}

}
