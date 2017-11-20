package com.example.adam.servicebuddy.models;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;

import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;

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
    static Context context = AppSingleton.getInstance().getAppContext();


    public enum component {
        ENGINE_OIL_FILTER(context.getString(R.string.engine_oil_filter)),
        ENGINE_OIL(context.getString(R.string.engine_oil)),
        TRANSMISSION_OIL(context.getString(R.string.transmission_oil)),
        TRANSMISSION_OIL_FILTER(context.getString(R.string.transmission_oil_filter)),
        AIR_FILTER(context.getString(R.string.air_filter));
     /*   CAB_AIR_FILTER,
        COOLANT,
        FUEL_FILTER,
        HYDRAULIC_OIL_FILTER,
        HYDRAULIC_OIL,
        WIPER_BLADES,
        DEF_FILTER,
        TIRES_FRONT,
        TIRES_REAR,
        DIFFERENTIAL_OIL_FRONT,
        DIFFERENTIAL_OIL_REAR,
        TRANSFER_CASE_OIL_FRONT,
        TRANSFER_CASE_OIL_REAR,
        GREASE;
*/

        private String name;

        component(String text){
            name = text;
        }
        public String getText(){
            return this.name;
        }
    }

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public void setOdometer(Date date, Integer value){
        odometerReadings.put(date,value);  //TODO validate if values are increasing over time
    }



}
