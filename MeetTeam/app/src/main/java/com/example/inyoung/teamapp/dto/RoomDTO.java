package com.example.inyoung.teamapp.dto;

/**
 * Created by inyoung on 2017-02-22.
 */

public class RoomDTO {
    private String room_Name;
    private String manager_Name;
    private int postion;

    public RoomDTO(String room_Name, String manager_Name,int position) {
        this.room_Name = room_Name;
        this.manager_Name = manager_Name;
        this.postion=position;
    }

    public String getRoom_Name() {
        return room_Name;
    }

    public String getManager_Name() {
        return manager_Name;
    }

    public int getPostion(){return postion;}
}
