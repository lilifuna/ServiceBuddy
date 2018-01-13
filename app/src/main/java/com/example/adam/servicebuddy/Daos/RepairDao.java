package com.example.adam.servicebuddy.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.PointsServiced;
import com.example.adam.servicebuddy.entities.RepairEntity;

import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */


@Dao
public interface RepairDao {

    @Query("SELECT * FROM repairs WHERE machineID = :machineId")
    List<RepairEntity> getAllMachineRepairs(int machineId);

    @Query("SELECT * FROM repairs WHERE :rId = id")
    RepairEntity findById(int rId);

    @Query("SELECT * FROM repairs WHERE :rId = id")
    PointsServiced getPointsServiced(int rId);

}
