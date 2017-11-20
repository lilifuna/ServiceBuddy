package com.example.adam.servicebuddy;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(List<MachineEntity> machines);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(MachineEntity machine);
}
