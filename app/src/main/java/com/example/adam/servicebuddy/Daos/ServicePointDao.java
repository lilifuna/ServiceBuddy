package com.example.adam.servicebuddy.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adam on 2017-11-20.
 */


@Dao
public interface ServicePointDao {

    @Query("SELECT * FROM servicePoints WHERE :mId =  machineID")
    List<ServicePointEntity> getServicePointsOfMachine(int mId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertAll(ServicePointEntity... servicePointEntities);

    @Delete
    void deleteAll(ServicePointEntity... servicePointEntities);
}
