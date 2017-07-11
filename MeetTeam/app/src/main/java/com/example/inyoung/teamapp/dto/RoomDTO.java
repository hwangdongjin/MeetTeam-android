package com.example.inyoung.teamapp.dto;

/**
 * Created by inyoung on 2017-02-22.
 */

public class RoomDTO {
    private String room_Title;
    private String manager_Name;
    private int postion;

    public RoomDTO(String room_Title, String manager_Name,int position) {
        this.room_Title = room_Title;
        this.manager_Name = manager_Name;
        this.postion=position;
    }

    public String getRoom_Title() {
        return room_Title;
    }

    public String getManager_Name() {
        return manager_Name;
    }

    public int getPostion(){return postion;}
}
