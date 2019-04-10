package com.example.doctorbooking.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.doctorbooking.util.Constants;


public class PreferencesHelper {
    private SharedPreferences sharedPreferences;


    PreferencesHelper(Activity activity) {
        sharedPreferences = activity.getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
    }

    public void putString( String key,  String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString( String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putBoolean( String key,  boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean( String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void putInt( String key,  boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public int getInt( String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
