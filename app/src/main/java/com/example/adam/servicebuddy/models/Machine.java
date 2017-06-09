package com.example.adam.servicebuddy.models;

import android.media.Image;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adam on 2017-06-07.
 */

public class Machine {

    int id;
    String name;
    String make;
    String model;
    String productionYear;
    Map<Date, Integer> odometerReadings;
    Image mainPhoto;



    public Machine(){
        odometerReadings = new HashMap<Date, Integer>();

    }

    public Machine(String name, String make, String model, String productionYear, int id) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.id = id;

        odometerReadings = new HashMap<Date, Integer>();
    }
}
