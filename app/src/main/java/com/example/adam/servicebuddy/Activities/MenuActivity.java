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

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.Repair;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.entities.OdometerReadingEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;

import java.util.Date;

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

       // if(appSingleton.getLoggedUserId() != -1){
            transaction.replace(R.id.menuContainer, fragmentMenu);
       // }
       // else{
         //   transaction.replace(R.id.menuContainer, fragmentInavailable);
        //}

        transaction.commit();


       // createDummyData();

    }

    private void createDummyData() {
        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
        MachineEntity machine = new MachineEntity();
        machine.setMake("XD");
        machine.setModel(";____;");
        machine.setName("Dupa cycki");
        machine.setProductionDate(2137);
        MachineEntity machine1 = new MachineEntity();
        machine1.setMake("XDD");
        machine1.setModel(";___fdf_;");
        machine1.setName("Dupa fdsfcycki");
        machine1.setProductionDate(2137);
        db.machineDao().insertAll(machine1);
        db.machineDao().insertAll(machine);

        OdometerReadingEntity odometer = new OdometerReadingEntity();
        odometer.setReadingTime(new Date());
        odometer.setMachineID(machine.getId());
        odometer.setOdometerReading(4);
        OdometerReadingEntity odometer1 = new OdometerReadingEntity();
        odometer1.setReadingTime(new Date());
        odometer1.setMachineID(machine1.getId());
        odometer1.setOdometerReading(4);
        db.odometerReadingDao().insertAll(odometer);
        db.odometerReadingDao().insertAll(odometer1);

        RepairEntity repair = new RepairEntity();
        repair.setMachineID(machine.getId());
        repair.setOperatorId(0);
        repair.setRepairDate(new Date());
        OdometerReadingEntity repairOdometer = new OdometerReadingEntity();
        repairOdometer.setOdometerReading(6);
        repairOdometer.setMachineID(machine.getId());
        repairOdometer.setReadingTime(new Date());
        db.odometerReadingDao().insertAll(repairOdometer);
        repair.setOdometerReadingId(repairOdometer.id);
        db.repairDao().insertAll(repair);
    }

}
