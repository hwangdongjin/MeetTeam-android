package com.example.inyoung.teamapp.dto;

import android.widget.CheckBox;

/**
 * Created by MYpc on 2017-07-11.
 */

public class CheckAddDTO {


    private String manager_Name;
    private String manager_Do;
    private CheckBox checkBox;
    private boolean check;

    public CheckAddDTO(String manager_Name,String manager_Do,boolean check) {
        this.manager_Name = manager_Name;
        this.manager_Do=manager_Do;
        this.check = check;

    }

    public void setManager_Name(String manager_Name) {
        this.manager_Name = manager_Name;
    }

    public void setManager_Do(String manager_Do) {
        this.manager_Do = manager_Do;
    }

    public void setCheckBox(boolean check){this.checkBox.setChecked(check);}


    public String getManager_Name() {
        return manager_Name;
    }

    public  String getManager_Do(){return  manager_Do; }

    public boolean getCheck(){return check;}
}
