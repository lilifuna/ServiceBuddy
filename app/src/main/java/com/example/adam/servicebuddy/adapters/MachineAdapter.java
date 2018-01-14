package com.example.adam.servicebuddy.adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudinary.Cloudinary;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adam on 2017-06-10.
 */

public class MachineAdapter extends BaseAdapter {

    Context context;
    List<MachineEntity> machines;


    public MachineAdapter(List<MachineEntity> machines, Context context){
        this.machines = machines;
        this.context = context;

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
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_row_machine_photo);
        TextView prodDateTextView = (TextView) rowView.findViewById(R.id.prodDateTextView);
        TextView odometerTextView = (TextView) rowView.findViewById(R.id.machineOdometerTextView);
        TextView brandTextView = (TextView) rowView.findViewById(R.id.machineBrandTextView);
        TextView modelTextView = (TextView) rowView.findViewById(R.id.machineModelTextView);

        prodDateTextView.setText(machines.get(position).getProductionDate());
        odometerTextView.setText(machines.get(position).);
        textView.setText(machines.get(position).getName());
        Picasso.with(context).load("https://st.mascus.com/imagetilewm/product/dd8a7989/manitou-mlt-627-t-turbo-4x4x4,3e1164d1.jpg").into(imageView);

        return rowView;
    }
}
