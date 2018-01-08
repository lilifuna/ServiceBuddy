package com.example.adam.servicebuddy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Adam on 2017-06-10.
 */

public class AppSingleton  {

    private Context context;
    private static AppSingleton mInstance;
    public static Cloudinary cloud;


    public static AppSingleton getInstance(Context context){
        if (mInstance == null) {
            mInstance = new AppSingleton();
            mInstance.initialize(context);
        }
        return mInstance;
    }


    private void cloudinaryInitialization(){
        HashMap config = new HashMap<>();
        config.put("cloud_name", "servicebuddy");
        config.put("api_key", "827279383419872");
        config.put("api_secret", "NoAKQ0DtaGSd4ePjDffEUnge7eg");
        cloud = new Cloudinary(config);
    }

    private AppSingleton(){

    }

    public void initialize(Context context){
        this.context = context;
        cloudinaryInitialization();
        SharedPreferences pref = context.getSharedPreferences("SessionData", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("UserId",0);
        editor.apply();
    }

    public int getLoggedUserId(){
        SharedPreferences preferences = context.getSharedPreferences("SessionData", Activity.MODE_PRIVATE);
        int userID = preferences.getInt("UserId", -1);
        return userID;
    }

    public void logOut(){
        SharedPreferences pref = context.getSharedPreferences("SessionData", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("UserId",0);
        editor.apply();
    }

    public Context getAppContext(){
        return context;
    }
}
