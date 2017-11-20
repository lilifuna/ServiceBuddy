package com.example.adam.servicebuddy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Created by Adam on 2017-11-20.
 */

@Entity(tableName = "pointServiced",
        primaryKeys = {"serviceID", "servicePointID"},
        foreignKeys = @ForeignKey(entity = ServicePoint.class,
                                    parentColumns = "pointID",
                                    childColumns = "servicePointID"))

public class PointServicedEntity implements PointServiced{

    int serviceID;
    int servicePointID;

}
