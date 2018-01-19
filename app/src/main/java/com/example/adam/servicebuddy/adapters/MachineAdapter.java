package com.example.adam.servicebuddy.adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudinary.Cloudinary;
import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.entities.OdometerReadingEntity;
import com.example.adam.servicebuddy.entities.PointServicedEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Adam on 2017-06-10.
 */

public class MachineAdapter extends BaseAdapter {

    Context context;
    List<MachineEntity> machines;
    public List<Boolean> needService;
    AppDatabase db;

    public MachineAdapter(List<MachineEntity> machines, Context context){
        this.machines = machines;
        this.context = context;
        needService = new ArrayList<>(Collections.nCopies(machines.size(), false));
        db = AppDatabase.getAppDatabase(context);
    }


    @Override
    public int getCount() {
        return machines.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return  machines.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.machine_list_row, parent, false);


        TextView textView = (TextView) rowView.findViewById(R.id.machine_name);
        String machineName = machines.get(position).getName();
       textView.setText(machineName);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_row_machine_photo);
        Picasso.with(context).load("https://st.mascus.com/imagetilewm/product/dd8a7989/manitou-mlt-627-t-turbo-4x4x4,3e1164d1.jpg").into(imageView);

        TextView prodDateTextView = (TextView) rowView.findViewById(R.id.prodDateTextView);
        Integer productionDate = machines.get(position).getProductionDate();
        prodDateTextView.setText(productionDate.toString());

        TextView odometerTextView = (TextView) rowView.findViewById(R.id.machineOdometerTextView);
        int machineId = machines.get(position).getId();
        OdometerReadingEntity odometerReading = db.odometerReadingDao().getMostRecentOdometerReading(machineId);
        Integer mostRecentOdometerReading = 0;
        if(odometerReading != null){
            mostRecentOdometerReading = odometerReading.getOdometerReading();
            odometerTextView.setText(mostRecentOdometerReading.toString());
        }


        TextView brandTextView = (TextView) rowView.findViewById(R.id.machineBrandTextView);
        String machineBrand = machines.get(position).getMake();
         brandTextView.setText(machineBrand);

        TextView modelTextView = (TextView) rowView.findViewById(R.id.machineModelTextView);
        String machineModel = machines.get(position).getModel();
        modelTextView.setText(machineModel);

        ImageView serviceAlert = (ImageView) rowView.findViewById(R.id.serviceAlertImageView);
        boolean requireService = false;
        List<RepairEntity> repairEntities = db.repairDao().getAllMachineRepairs(machines.get(position).getId());
        List<ServicePointEntity> machineServicePoints = db.servicePointDao().getServicePointsOfMachine(machines.get(position).getId());


        for(ServicePointEntity servicePoint : machineServicePoints){
            RepairEntity lastService = db.repairDao().getLastService(servicePoint.getId());
            if(lastService == null){// WHEN GIVEN SERVICE POINT HASN'T YET BEEN SERVICED
                Log.v("DEBUG", "No previous service for machine" + machineName + " service point " + servicePoint.getName());
                continue;
            }

            int lastServiceOdometerReading = db.odometerReadingDao().getOdometerReadingAtTimeOfRepair(lastService.getId()).getOdometerReading();
            int hoursSinceLastService = mostRecentOdometerReading - lastServiceOdometerReading;
            Log.v("DEBUG","Hours since last service of" + servicePoint.getName() + machineName + " with interval of " + servicePoint.getInterval() + "h = " + hoursSinceLastService);
            if(hoursSinceLastService >= servicePoint.getInterval() ){
                serviceAlert.setVisibility(View.VISIBLE);
                serviceAlert.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_alert));
                needService.set(position, true);
            }
            else{
                //serviceAlert.setVisibility(View.INVISIBLE);
                serviceAlert.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_done));
                needService.set(position, false);
            }
        }

        return rowView;
    }




}
