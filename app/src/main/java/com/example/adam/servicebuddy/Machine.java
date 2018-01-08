package com.example.adam.servicebuddy;

import java.util.Date;

/**
 * Created by Adam on 2017-11-13.
 */

public interface Machine {

    public String getId();
    public String getName();
    public void setName(String name);
    public String getMake();
    public void setMake(String make);
    public int getProductionDate();
    public void setProductionDate(int productionDate);

}
