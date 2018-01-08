package com.example.adam.servicebuddy.repositories;

import com.example.adam.servicebuddy.Daos.RepairDao;

import javax.inject.Inject;

/**
 * Created by Adam on 2018-01-02.
 */

public class RepairRepository {

    private final RepairDao repairDao;

    @Inject
    public RepairRepository(RepairDao repairDao){
        this.repairDao = repairDao;
    }
}
