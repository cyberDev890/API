package com.example.acara31;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

//deklarasi key untuk data yang disimpan
    static final String KEY_USER_REGISTER ="user", KEY_PASS_REGISTER ="pass";
    static final String KEY_USERNAME_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_LOGIN = "Status_logged_in";
    Context context;
    //pendeklarasian Shared Preferences yang berdasarkan parameter context
    Preferences(Context context) {
        this.context = context;
    }
//fungsi untuk mengambil data Shared Preferences
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

//deklarasi edit data Shared Preferences dan mengubah data yang ada di Shared Preferences
    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_REGISTER, username);
        editor.apply();
    }
    //mengambil nilai dari key user_registered yang berupa String
    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_REGISTER,"");
    }

//deklarasi edit data Shared Preferences dan mengubah data yang ada di Shared Preferences
    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_REGISTER, password);
        editor.apply();
    }
//mengembalikan nilai dari key pass_registered yang berupa String
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_REGISTER,"");
    }

//deklarasi edit data Shared Preferences dan mengubah data yang ada di Shared Preferences yang memiliki key KEY_USERNAME_LOGIN dengan paramater username
    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_LOGIN, username);
        editor.apply();
    }
//mengembalikan nilai dari key KEY_USERNAME_LOGIN yang berupa String
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_LOGIN,"");
    }

//deklarasi edit data Shared Preferences dan mengubah data yang ada di Shared Preferences yang memiliki key KEY_STATUS_LOGIN dengan paramater status
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_LOGIN,status);
        editor.apply();
    }
//mengembalikan nilai dari key KEY_STATUS_LOGIN yang berupa boolean
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_LOGIN,false);
    }

//menghapus data yang ada di Shared Preferences
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_LOGIN);
        editor.remove(KEY_STATUS_LOGIN);
        editor.apply();
    }
}
