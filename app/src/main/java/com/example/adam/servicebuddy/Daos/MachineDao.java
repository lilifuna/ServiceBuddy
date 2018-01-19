package com.example.adam.servicebuddy.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.Machine;
import com.example.adam.servicebuddy.ServicePoint;
import com.example.adam.servicebuddy.entities.MachineEntity;

import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */

@Dao
public interface MachineDao {


    @Query("SELECT * FROM machines")
    List<MachineEntity> getAllMachines();

    @Query("SELECT * FROM machines WHERE name = :machineName")
    List<MachineEntity> getAllMachinesByName(String machineName);

    @Query("SELECT * FROM machines WHERE :mId = id")
    MachineEntity getMachineById(int mId);



  /*  @Query("SELECT servicePoints.name FROM servicePoints " +
            "JOIN machines ON machines.id = servicePoints.machineID")
    List<ServicePoint> getAllServicePoints(int machineID);*/

    //@Insert(onConflict = OnConflictStrategy.ABORT)
    //void insertAll(List<MachineEntity> machines);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    List<Long> insertAll(MachineEntity... machines);
}
