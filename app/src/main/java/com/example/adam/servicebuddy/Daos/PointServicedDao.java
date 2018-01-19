package com.example.adam.servicebuddy.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.entities.PointServicedEntity;

import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */

@Dao
public interface PointServicedDao {

    @Query("SELECT * FROM pointsServiced WHERE :rId = repairID")
    public List<PointServicedEntity> getPointsServicedDuringRepair(int rId);



    @Insert
    public void insertAll(PointServicedEntity... pointServicedEntities);

    @Delete
    public void deleteAll(PointServicedEntity... pointServicedEntities);

}
