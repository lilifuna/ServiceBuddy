package com.example.adam.servicebuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.PointServicedEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Adam on 2018-01-13.
 */

public class PointServicedAdapter extends BaseAdapter {

    Context context;
    List<ServicePointEntity> servicePointEntities;
    List<Boolean> selectedPoints;
    AppDatabase db;

    public PointServicedAdapter(List<ServicePointEntity> servicePoints, Context context){
        this.servicePointEntities = servicePoints;
        this.context = context;
        this.selectedPoints = new ArrayList<Boolean>(Collections.nCopies(servicePointEntities.size(), false));
        db = AppDatabase.getAppDatabase(context);
    }


    @Override
    public int getCount() {
        return servicePointEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return  servicePointEntities.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.servicepoint_list_row, parent, false);
        CheckBox pointServicedCheckBox = (CheckBox) rowView.findViewById(R.id.pointServicedCheckBox);
        pointServicedCheckBox.setText(servicePointEntities.get(position).getName());

        pointServicedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedPoints.set(position, !selectedPoints.get(position));
            }
        });


        int machineId = servicePointEntities.get(0).getMachineID();
        int mostRecentOdometerReading = db.odometerReadingDao().getMostRecentOdometerReading(machineId).getOdometerReading();

        ImageView alertImage = (ImageView) rowView.findViewById(R.id.requireServiceAlertImageView);
        RepairEntity lastService = db.repairDao().getLastService(servicePointEntities.get(position).getId());
        if(lastService == null) return rowView; // WHEN GIVEN SERVICE POINT HASN'T YET BEEN SERVICED

        alertImage.setVisibility(View.INVISIBLE);
        int lastServiceOdometerReading = db.odometerReadingDao().getOdometerReadingAtTimeOfRepair(lastService.getId()).getOdometerReading();
        int hoursSinceLastService = mostRecentOdometerReading - lastServiceOdometerReading;

        if(hoursSinceLastService >= servicePointEntities.get(position).getInterval() ){
            alertImage.setVisibility(View.VISIBLE);
        }

        return rowView;
    }

    public List<PointServicedEntity> getPointsServiced(){
        List<PointServicedEntity> result = new ArrayList<PointServicedEntity>();
        for(int i = 0; i < servicePointEntities.size(); i++){
            if(selectedPoints.get(i)){
                PointServicedEntity temp = new PointServicedEntity();
                temp.setServicePointId(servicePointEntities.get(i).getId());
                result.add(temp);
            }
        }

        return result;

    }
}
