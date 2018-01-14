package com.example.adam.servicebuddy;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.*;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.adam.servicebuddy.Daos.MachineDao;
import com.example.adam.servicebuddy.Daos.OdometerReadingDao;
import com.example.adam.servicebuddy.Daos.PointServicedDao;
import com.example.adam.servicebuddy.Daos.RepairDao;
import com.example.adam.servicebuddy.Daos.ServicePointDao;
import com.example.adam.servicebuddy.Daos.UserDao;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.entities.OdometerReadingEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;
import com.example.adam.servicebuddy.entities.UserEntity;

/**
 * Created by Adam on 2017-11-20.
 */
@Database (entities = {MachineEntity.class, OdometerReadingEntity.class RepairEntity.class, ServicePointEntity.class, UserEntity.class}, version = 4, exportSchema = false)
@android.arch.persistence.room.TypeConverters({TypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;


    public abstract MachineDao machineDao();
    public abstract PointServicedDao pointServicedDao();
    public abstract RepairDao repairDao();
    public abstract ServicePointDao servicePointDao();
    public abstract UserDao userDao();
    public abstract OdometerReadingDao odometerReadingDao();


    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app-database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }


    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
