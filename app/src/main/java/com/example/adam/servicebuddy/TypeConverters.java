package com.example.adam.servicebuddy;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Adam on 2018-01-03.
 */

public class TypeConverters {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
