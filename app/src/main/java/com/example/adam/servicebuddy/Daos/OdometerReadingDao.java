package com.example.adam.servicebuddy.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.entities.OdometerReadingEntity;

import java.util.List;

/**
 * Created by Adam on 2018-01-14.
 */

@Dao
public interface OdometerReadingDao {

    @Query("SELECT * FROM odometerReadings WHERE :mId = machineID")
    public List<OdometerReadingEntity> getAllMachineOdometerReadings(int mId);

    @Query("SELECT odometerReading FROM odometerReadings WHERE :mID = machineID SORT BY")

    @Insert
    public void insertAll(OdometerReadingEntity... odometerReadingEntities);

    @Delete
    public void deleteAll(OdometerReadingEntity... odometerReadingEntities);





}
