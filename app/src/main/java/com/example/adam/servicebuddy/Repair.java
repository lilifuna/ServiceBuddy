package com.example.adam.servicebuddy;

import java.util.Date;

/**
 * Created by Adam on 2017-11-20.
 */

public interface Repair {

    public int getId();
    public int getOperatorId();
    public int getMachineId();
    public Date getRepairDate();


}
