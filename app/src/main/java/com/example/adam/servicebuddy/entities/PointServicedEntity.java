package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Adam on 2018-01-15.
 */

@Entity(tableName = "pointsServiced")
public class PointServicedEntity {

    @PrimaryKey(autoGenerate = true) public int id;



    int repairId;
    int servicePointId;

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getServicePointId() {
        return servicePointId;
    }

    public void setServicePointId(int servicePointId) {
        this.servicePointId = servicePointId;
    }
}
