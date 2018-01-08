package com.example.adam.servicebuddy;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Adam on 2017-06-10.
 */

public class MachineAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> photosUrls;
    ArrayList<String> names;


    public MachineAdapter(ArrayList<String> photosUrls, ArrayList<String> names, Context context){
        this.photosUrls = photosUrls;
        this.names = names;
        this.context = context;

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.machine_list_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.machine_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_row_machine_photo);
        textView.setText(names.get(position));
        Picasso.with(context).load(photosUrls.get(position)).into(imageView);

        return rowView;
    }
}
