package com.example.inyoung.teamapp.RetroFit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MYpc on 2017-05-24.
 */

public class SharedPreferenceUtil {
    public static final String APP_SHARED_PREFS = "thisApp.SharedPreference";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String sess;
    private String id;
    private String name;
    private String roomTitle;
    public static int checkNum=0;


    public void setCheckNum() {
        editor.putInt("checkNum",4);
        editor.commit();
    }

    private String date;
    private int checkboxcount;


    public void setSess(String sess) {
        editor.putString("sesssion", sess);
        editor.commit();

    }
    public void setRoomTitle(String roomTitle){

        editor.putString("roomTitle",roomTitle);
        editor.commit();

    }

    public void setId(String id) {
        editor.putString("id", id);
        editor.commit();
    }
    public void setName(String name){
        editor.putString("name",name);
        editor.commit();

    }

    public void setDate(String date){
        editor.putString("date",date);
        editor.commit();
    }

    public void setCheckboxcount(int checkboxcount){

        editor.putInt("checkboxcount",checkboxcount);
        editor.commit();
    }

    public SharedPreferenceUtil(Context context) {
        this.sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public String getSess() {
        return sharedPreferences.getString("sesssion", "defValue");
    }
    public String getName() {return sharedPreferences.getString("name","dafValue");}
    public String getRoomTitle(){return  sharedPreferences.getString("roomTitle","dafValue");}
    public String getDate() {return sharedPreferences.getString("date","dafValue");}
    public int getCheckNum(){return sharedPreferences.getInt("checkNum", Integer.parseInt("8282"));}
    public int getCheckboxcount(){return sharedPreferences.getInt("checkboxcount",999);}

}




















