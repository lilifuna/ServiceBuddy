package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.MachineEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MachineDetails extends AppCompatActivity {

    AppDatabase db;
    @BindView(R.id.machineNameTextView) TextView nameTextView;
    @BindView(R.id.odometerTextView)    TextView odometerTextView;
    @BindView(R.id.prodYearTextView)    TextView productionYearTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_details);
        ButterKnife.bind(this);

        db = AppDatabase.getAppDatabase(getApplicationContext());
        Intent intent = getIntent();
        int machineId = Integer.parseInt(intent.getStringExtra("machineID"));
        MachineEntity machine = db.machineDao().getMachineById(machineId);

        nameTextView.setText(machine.getName());
        odometerTextView.setText("2137");
        productionYearTextView.setText(machine.getProductionDate());

    }
}
