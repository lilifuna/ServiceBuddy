package com.example.adam.servicebuddy;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Adam on 2017-11-20.
 */


@Entity(tableName = "servicePoints")
public class ServicePointEntity implements ServicePoint {

    @PrimaryKey  int pointId;
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    int interval;                   //interval in hours of operation

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getInterval() {
        return interval;
    }
}
