package com.example.adam.servicebuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.List;

/**
 * Created by Adam on 2018-01-13.
 */

public class ServicePointAddAdapter extends BaseAdapter {

    Context context;
    List<ServicePointEntity> servicePointEntities;
    AppDatabase db;

    public ServicePointAddAdapter(List<ServicePointEntity> servicePoints, Context context){
        this.servicePointEntities = servicePoints;
        this.context = context;
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
        View rowView = inflater.inflate(R.layout.servicepoint_add_list_row, parent, false);
        CheckBox servicePointCheckBox = (CheckBox) rowView.findViewById(R.id.servicePointCheckBox);
        servicePointCheckBox.setText(servicePointEntities.get(position).getName());

        EditText intervalEditText = (EditText) rowView.findViewById(R.id.intervalEditText);
        intervalEditText.setText(servicePointEntities.get(position).getInterval());

        return rowView;
    }
}
