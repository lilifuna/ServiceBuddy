package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.adam.servicebuddy.Repair;

import java.util.Date;

/**
 * Created by Adam on 2017-11-20.
 */


@Entity (tableName = "repairs")
public class RepairEntity implements Repair {

    @PrimaryKey(autoGenerate = true) public int id;

    @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "operatorId")
        public int operatorId;

    @ForeignKey(entity = MachineEntity.class, parentColumns = "id", childColumns = "machineId")
        public int machineID;
    public Date repairDate;

    public int odometerReadingId;


    @Override
    public int getId() {
        return id;
    }
    @Override
    public int getOperatorId() {
        return operatorId;
    }
    @Override
    public int getMachineId() {
        return machineID;
    }
    public Date getRepairDate(){
        return repairDate;
    }
    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }
    public int getMachineID() {
        return machineID;
    }
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }
    public int getOdometerReadingId() {
        return odometerReadingId;
    }
    public void setOdometerReadingId(int odometerReadingId) {
        this.odometerReadingId = odometerReadingId;
    }




}
