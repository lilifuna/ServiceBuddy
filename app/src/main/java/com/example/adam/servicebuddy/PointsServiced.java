package com.example.adam.servicebuddy;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */


public class PointsServiced {

    @Embedded
    public RepairEntity repair;

    @Relation(parentColumn = "id", entityColumn = "id", entity = ServicePointEntity.class)
    public List<ServicePointEntity> servicedPoints;

}
