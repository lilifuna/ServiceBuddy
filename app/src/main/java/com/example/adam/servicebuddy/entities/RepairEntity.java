package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import com.example.adam.servicebuddy.Repair;

import java.util.Date;

/**
 * Created by Adam on 2017-11-20.
 */


@Entity (tableName = "repairs")
public class RepairEntity implements Repair {

    @PrimaryKey public  int id;
    public int operatorId;
    public int machineID;
    public Date repairDate;


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getOperatorId() {
        return 0;
    }

    @Override
    public int getMachineId() {
        return 0;
    }

    public Date getRepairDate(){
        return repairDate;
    }





}
