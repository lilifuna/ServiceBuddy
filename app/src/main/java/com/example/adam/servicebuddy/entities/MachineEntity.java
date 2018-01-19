package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import com.example.adam.servicebuddy.Machine;

import java.util.Date;
import java.util.List;

/**
 * Created by Adam on 2017-11-20.
 */

@Entity (tableName = "machines")
public class MachineEntity implements Machine {

    @PrimaryKey(autoGenerate = true) public int id;
    public String name;
    public String make;
    public String model;
    public int productionDate;


    public String photoUrl;

    @Ignore
    public MachineEntity(String name, String make, int productionDate){
        this.name = name;
        this.make = make;
        this.productionDate = productionDate;
    }

    @Ignore
    public MachineEntity(Machine machine){
        this.id = machine.getId();
        this.name = machine.getName();
        this.make = machine.getMake();
        this.productionDate = machine.getProductionDate();
    }


    public MachineEntity(){

    }


    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}
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
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
