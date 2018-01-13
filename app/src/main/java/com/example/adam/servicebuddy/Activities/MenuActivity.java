package com.example.adam.servicebuddy.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;

import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        AppSingleton appSingleton = AppSingleton.getInstance(getApplicationContext());

        InavailableFragment fragmentInavailable = new InavailableFragment();
        MenuActivityFragment fragmentMenu = new MenuActivityFragment();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //if(appSingleton.getLoggedUserId() != 0){
            transaction.replace(R.id.menuContainer, fragmentMenu);
       // }
       // else{
          //  transaction.replace(R.id.menuContainer, fragmentInavailable);
       // }

        transaction.commit();
    }

}
