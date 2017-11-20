package com.example.adam.servicebuddy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */


@Dao
public interface RepairDao {

    @Query("SELECT * FROM repairs WHERE machineID = :machineId")
    List<Repair> getAllMachineRepairs(int machineId);


}
