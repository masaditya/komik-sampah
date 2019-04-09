package com.adityaeka.sampahisasi.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.adityaeka.sampahisasi.preferences.Constant;

public class Settings {


    public SharedPreferences preferences;

    public Settings(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUser() {
        return preferences.getString(Constant.SESSION, null);
    }

    public void setUser(String user) {
        preferences.edit()
                .putString(Constant.SESSION, user)
                .apply();
    }

    public void removeUser() {
        preferences.edit()
                .remove(Constant.SESSION)
                .apply();
    }
}
