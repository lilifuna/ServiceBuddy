package com.example.adam.servicebuddy.repositories;

import com.example.adam.servicebuddy.Daos.PointServicedDao;
import com.example.adam.servicebuddy.Daos.RepairDao;
import com.example.adam.servicebuddy.Daos.ServicePointDao;

import javax.inject.Inject;

/**
 * Created by Adam on 2018-01-02.
 */

public class PointServicedRepository {

    private final PointServicedDao pointServicedDao;

    @Inject
    public PointServicedRepository(PointServicedDao pointServicedDao){
        this.pointServicedDao = pointServicedDao;
    }
}
