package com.example.adam.servicebuddy;

import android.app.Application;
import android.content.Context;

import com.cloudinary.Cloudinary;

import java.util.HashMap;

/**
 * Created by Adam on 2017-06-10.
 */

public class AppSingleton  {

    private Context context;
    private static AppSingleton mInstance;
    public static Cloudinary cloud;


    public static AppSingleton getInstance(){
        if (mInstance == null) mInstance = getSync();
        return mInstance;
    }

    private static synchronized AppSingleton getSync() {
        if (mInstance == null){
            mInstance = new AppSingleton();
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
    }


    public Context getAppContext(){
        return context;
    }
}
