package com.example.adam.servicebuddy.repositories;

import android.arch.lifecycle.LiveData;

import com.example.adam.servicebuddy.Daos.MachineDao;
import com.example.adam.servicebuddy.Daos.PointServicedDao;
import com.example.adam.servicebuddy.Daos.RepairDao;
import com.example.adam.servicebuddy.Daos.ServicePointDao;
import com.example.adam.servicebuddy.entities.MachineEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adam on 2018-01-02.
 */

public class MachineRepository {

    private final MachineDao machineDao;


    @Inject
    public MachineRepository(MachineDao machineDao){
        this.machineDao = machineDao;

    }

    public LiveData<List<MachineEntity>> getMachineByName(String name){
        return machineDao.getAllMachinesByName(name);
    }

    public LiveData<List<MachineEntity>> getAllMachines(){
        return machineDao.getAllMachines();
    }

}
