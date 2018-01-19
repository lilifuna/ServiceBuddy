package com.example.adam.servicebuddy.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Adam on 2018-01-13.
 */

public class ServicePointAddAdapter extends BaseAdapter {

    Context context;
    List<String> names;
    List<String> intervals;
    List<Boolean> selectedPoints;
    AppDatabase db;

    public ServicePointAddAdapter(ArrayList<String> servicePoints,ArrayList<String> intervals,  List<Boolean> isChecked, Context context){
        this.names = servicePoints;
        this.context = context;
        this.intervals = intervals;
        this.selectedPoints = isChecked;

        db = AppDatabase.getAppDatabase(context);
    }

    public ServicePointAddAdapter(ArrayList<String> servicePoints, Context context){
        this.names = servicePoints;
        this.context = context;
        this.selectedPoints = new ArrayList<Boolean>(Collections.nCopies(names.size(), false));
        this.intervals = new ArrayList<String>(Collections.nCopies(names.size(), "0"));
        db = AppDatabase.getAppDatabase(context);
    }


    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return  0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.servicepoint_add_list_row, parent, false);
        CheckBox servicePointCheckBox = (CheckBox) rowView.findViewById(R.id.servicePointCheckBox);
        servicePointCheckBox.setText(names.get(position));
        servicePointCheckBox.setChecked(selectedPoints.get(position));

        EditText intervalEditText = (EditText) rowView.findViewById(R.id.intervalEditText);
        intervalEditText.setText(intervals.get(position));

        servicePointCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    selectedPoints.set(position, true);
                }
                else{
                    selectedPoints.set(position, false);
                }
            }
        });

        intervalEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                intervals.set(position, s.toString());
            }
        });



        return rowView;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<String> intervals) {
        this.intervals = intervals;
    }

    public List<Boolean> getSelectedPoints() {
        return selectedPoints;
    }

    public void setSelectedPoints(List<Boolean> selectedPoints) {
        this.selectedPoints = selectedPoints;
    }

    public int getSelectedPointsCount(){
        int count = 0;
        for(Boolean point : selectedPoints){
            if(point) count++;
        }
        return count;
    }

    public ServicePointEntity[] getEntitiesToCreate(int machineID){
        ServicePointEntity[] result = new ServicePointEntity[getSelectedPointsCount()];
        int pointer = 0;
        for (int i = 0; i < selectedPoints.size() ; i++) {
            if(selectedPoints.get(i)){
                ServicePointEntity temp = new ServicePointEntity();
                temp.setName(names.get(i));
                temp.setMachineID(machineID);
                temp.setInterval(Integer.parseInt(intervals.get(i)));
                result[pointer++] = temp;
            }
        }

        return result;
    }
}
