package com.example.adam.servicebuddy;

import java.util.Date;
import java.util.List;

/**
 * Created by Adam on 2017-11-13.
 */

public interface IDataProvider {


    public Machine getMachineById(int machineID);
    public List<Machine> getMachinesToService();
    public List<Machine> getMachinesByName(String name);
    public List<Machine> getMachinesServicedByOperatorFromDate(int operatorID, Date dateFrom);

    public boolean addMachine(Machine machine);
    public boolean addService(Service service);
}
