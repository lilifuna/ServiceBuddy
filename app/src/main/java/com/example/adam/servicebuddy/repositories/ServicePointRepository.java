package com.example.adam.servicebuddy.repositories;

import com.example.adam.servicebuddy.Daos.ServicePointDao;
import com.example.adam.servicebuddy.ServicePoint;

import javax.inject.Inject;

/**
 * Created by Adam on 2018-01-02.
 */

public class ServicePointRepository {

    private final ServicePointDao servicePointDao;

    @Inject
    public ServicePointRepository(ServicePointDao servicePointDao){
        this.servicePointDao = servicePointDao;
    }



}
