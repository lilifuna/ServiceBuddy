package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.adam.servicebuddy.Machine;

import java.util.Date;

/**
 * Created by Adam on 2017-11-20.
 */

@Entity (tableName = "machines")
public class MachineEntity implements Machine {

    @PrimaryKey int id;
    String name;
    String make;
    int productionDate;


    public MachineEntity(int id, String name, String make, int productionDate){
        this.id = id;
        this.name = name;
        this.make = make;
        this.productionDate = productionDate;
    }

    public MachineEntity(Machine machine){
        this.id = machine.getId();
        this.name = machine.getName();
        this.make = machine.getMake();
        this.productionDate = machine.getProductionDate();
    }





    @Override
    public int getId(){
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public int getProductionDate() {
        return productionDate;
    }

    @Override
    public void setProductionDate(int productionDate) {
        this.productionDate = productionDate;
    }
}
