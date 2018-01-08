package com.example.adam.servicebuddy.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adam.servicebuddy.entities.UserEntity;

import java.util.List;

/**
 * Created by Adam on 2018-01-03.
 */

@Dao
public interface UserDao {


    @Query("SELECT * FROM Users WHERE login = :name")
    UserEntity findByName(String name);

    @Query("SELECT * FROM Users")
    List<UserEntity> getAllUsers();

    //@Query("SELECT * FROM Users WHERE :uid = id")
   // UserEntity getUserById(int uid);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserEntity... users);

    @Delete
    void delete(UserEntity user);
}
