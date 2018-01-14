package com.example.adam.servicebuddy.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.adapters.PointServicedAdapter;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddServiceActivity extends AppCompatActivity {

    @BindView(R.id.availableServicePointsListView) ListView availablePointsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        ButterKnife.bind(this);
        PointServicedAdapter adapter;

        AppDatabase db = AppDatabase.getAppDatabase(this);
        List<ServicePointEntity> availableServicePoints = db.servicePointDao().getServicePointsOfMachine(getIntent().getIntExtra("machineID", 0));
        adapter = new PointServicedAdapter(availableServicePoints, this);
        availablePointsListView.setAdapter(adapter);

    }
}
