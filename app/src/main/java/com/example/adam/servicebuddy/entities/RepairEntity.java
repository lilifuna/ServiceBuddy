package com.example.adam.servicebuddy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Adam on 2017-11-20.
 */


@Entity (tableName = "repairs")
public class RepairEntity implements Repair{

    @PrimaryKey  int id;
    int operatorId;
    int machineID;
    Date repairDate;


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
