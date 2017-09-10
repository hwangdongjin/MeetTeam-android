package com.example.inyoung.teamapp.dto;

/**
 * Created by MYpc on 2017-09-10.
 */

public class ChatShowDTO {
    private String message;
    private String userName;

    public ChatShowDTO(String message, String userName){
        this.message=message;
        this.userName=userName;
    }
    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }
}
