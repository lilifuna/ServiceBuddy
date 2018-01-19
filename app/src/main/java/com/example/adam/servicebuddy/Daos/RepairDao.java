package com.example.adam.servicebuddy.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.PointsServiced;
import com.example.adam.servicebuddy.entities.RepairEntity;

import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */


@Dao
public interface RepairDao {

    @Query("SELECT * FROM repairs WHERE machineID = :machineId ORDER BY repairDate DESC")
    List<RepairEntity> getAllMachineRepairs(int machineId);

    @Query("SELECT * FROM repairs WHERE :rId = id")
    RepairEntity findById(int rId);

    @Query("SELECT * FROM pointsServiced JOIN repairs ON pointsServiced.repairId = repairs.id WHERE :pointId = pointsServiced.servicePointId ORDER BY repairs.odometerReadingId DESC LIMIT 1")
    public RepairEntity getLastService(int pointId);

    @Query("SELECT * FROM repairs WHERE :rId = id")
    PointsServiced getPointsServiced(int rId);

    @Query("SELECT * FROM Users JOIN repairs ON Users.id = repairs.operatorId WHERE :uid = repairs.operatorId")
    List<RepairEntity> getRepairsMadeByOperator(int uid);

    @Insert
    public List<Long> insertAll(RepairEntity... repairEntities);

}
