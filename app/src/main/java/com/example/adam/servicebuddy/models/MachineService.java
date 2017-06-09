package com.example.adam.servicebuddy.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Adam on 2017-06-07.
 */

public class MachineService {

    public enum component{
        ENGINE_OIL_FILTER,
        ENGINE_OIL,
        TRANSMISSION_OIL,
        TRANSMISSION_OIL_FILTER,
        AIR_FILTER,
        CAB_AIR_FILTER,
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
        GREASE
    }


    Date date;

    List<component> componentsServiced = new ArrayList<>();

    public MachineService(){
        componentsServiced.add(component.ENGINE_OIL);
    }
}
