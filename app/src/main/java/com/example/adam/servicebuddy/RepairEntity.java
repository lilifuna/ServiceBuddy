package com.example.adam.servicebuddy;

import android.arch.persistence.room.Entity;

/**
 * Created by Adam on 2017-11-20.
 */


@Entity
public class RepairEntity implements Repair{

    int id;
    int operatorId;
    int machineID;


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
}
