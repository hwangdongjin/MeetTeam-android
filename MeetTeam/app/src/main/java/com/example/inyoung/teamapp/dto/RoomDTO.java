package com.example.inyoung.teamapp.dto;

/**
 * Created by inyoung on 2017-02-22.
 */

public class RoomDTO {
    private String room_Name;
    private String manager_Name;

    public RoomDTO(String room_Name, String manager_Name) {
        this.room_Name = room_Name;
        this.manager_Name = manager_Name;
    }

    public String getRoom_Name() {
        return room_Name;
    }

    public String getManager_Name() {
        return manager_Name;
    }

}
