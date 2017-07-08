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

    public void setSess(String sess) {
        editor.putString("sess", sess);
        editor.commit();

    }

    public void setId(String id) {
        editor.putString("id", id);
        editor.commit();
    }

    public SharedPreferenceUtil(Context context) {
        this.sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();

    }

    public String getSess() {
        return sharedPreferences.getString("sess", "defValue");
    }

}
