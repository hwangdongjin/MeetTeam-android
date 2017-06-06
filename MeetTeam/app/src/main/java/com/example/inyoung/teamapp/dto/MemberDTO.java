package com.example.inyoung.teamapp.dto;

/**
 * Created by inyoung on 2017-02-22.
 */

public class MemberDTO {
    private String mImage;
    private String mName;
    private String mData;

    public MemberDTO(String mImage, String mName, String mData) {
        this.mImage = mImage;
        this.mName = mName;
        this.mData = mData;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmName() {
        return mName;
    }

    public String getmData() {
        return mData;
    }
}
