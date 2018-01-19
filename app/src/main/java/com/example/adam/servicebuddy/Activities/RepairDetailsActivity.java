package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.PointsServiced;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepairDetailsActivity extends AppCompatActivity {

    AppDatabase db;
    @BindView(R.id.servicedPointsList) ListView servicedPointsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_details);
        ButterKnife.bind(this);
        db = AppDatabase.getAppDatabase(getApplicationContext());
        Intent intent = getIntent();
        int repairId = Integer.parseInt(intent.getStringExtra("repairID"));
        RepairEntity repairEntity = db.repairDao().findById(repairId);

        PointsServiced servicePoints = db.repairDao().getPointsServiced(repairId);
        final ArrayList<String> points = new ArrayList<>();
      //  for(ServicePointEntity point : servicePoints.servicedPoints){
          //  points.add(point.getName());
       // }
        servicePoints.servicedPoints.stream().forEach(s -> points.add(s.getName()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, points);
        servicedPointsList.setAdapter(adapter);
    }
}
