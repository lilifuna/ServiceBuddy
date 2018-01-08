package com.example.adam.servicebuddy.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.Repair;
import com.example.adam.servicebuddy.entities.RepairEntity;

import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */


@Dao
public interface RepairDao {

    @Query("SELECT * FROM repairs WHERE machineID = :machineId")
    List<RepairEntity> getAllMachineRepairs(int machineId);


}
