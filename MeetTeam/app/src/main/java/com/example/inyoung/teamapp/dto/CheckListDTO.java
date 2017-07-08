package com.example.inyoung.teamapp.dto;

/**
 * Created by MYpc on 2017-04-10.
 */

public class CheckListDTO {
    private String checkRoom_Name;
    private String manager_Name;
    private String manager_Do;

    public CheckListDTO(String checkRoom_Name, String manager_Name,String manager_Do) {
        this.checkRoom_Name = checkRoom_Name;
        this.manager_Name = manager_Name;
        this.manager_Do=manager_Do;
    }

    public String getCheck_RoomName() {return checkRoom_Name;}

    public String getManager_Name() {
        return manager_Name;
    }

    public  String getManager_Do(){return  manager_Do; }
}
