package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.example.adam.servicebuddy.PointServiced;
import com.example.adam.servicebuddy.ServicePoint;

/**
 * Created by Adam on 2017-11-20.
 */

@Entity(tableName = "pointServiced",
        primaryKeys = {"serviceID", "servicePointID"},
        foreignKeys = @ForeignKey(entity = ServicePointEntity.class,
                                    parentColumns = "pointId",
                                    childColumns = "servicePointID"))
public class PointServicedEntity implements PointServiced {

    public int serviceID;
    public int servicePointID;

}
