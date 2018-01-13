package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.adam.servicebuddy.Machine;
import com.example.adam.servicebuddy.ServicePoint;

/**
 * Created by Adam on 2017-11-20.
 */


@Entity(tableName = "servicePoints"
        ,foreignKeys = @ForeignKey(entity = MachineEntity.class,
                                    parentColumns = "id",
                                    childColumns = "machineID"))

public class ServicePointEntity implements ServicePoint {



    @PrimaryKey (autoGenerate = true) public int id;
    public String name;
    public int machineID;
    int interval;                   //interval in hours of operation

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getMachineID() {return machineID;}
    public void setMachineID(int machineID) {this.machineID = machineID;}
    public void setName(String name) {
        this.name = name;
    }
    public void setInterval(int interval) {
        this.interval = interval;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getInterval() {
        return interval;
    }
}
