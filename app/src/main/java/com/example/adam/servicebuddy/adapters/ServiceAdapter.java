package com.example.adam.servicebuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Adam on 2018-01-12.
 */

public class ServiceAdapter extends BaseAdapter {

    Context context;
    List<RepairEntity> repairs;
    AppDatabase db;

    public ServiceAdapter(List<RepairEntity> repairs, Context context){
        this.repairs = repairs;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }


    @Override
    public int getCount() {
        return repairs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return  repairs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.service_list_row, parent, false);
        TextView serviceDate = (TextView) rowView.findViewById(R.id.serviceDateTextBox);
        TextView operatorNameTextView = (TextView) rowView.findViewById(R.id.operatorNameTextBox);
        serviceDate.setText(repairs.get(position).getRepairDate().toString());
        String operatorName = db.userDao().findById(repairs.get(position).getOperatorId()).getName();
        operatorNameTextView.setText(operatorName);

        return rowView;
    }
}
