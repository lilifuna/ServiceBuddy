package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.adapters.ServiceAdapter;
import com.example.adam.servicebuddy.adapters.ServicePointAddAdapter;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MachineDetailsActivity extends AppCompatActivity {

    AppDatabase db;
    @BindView(R.id.machineNameTextView) TextView nameTextView;
    @BindView(R.id.odometerTextView)    TextView odometerTextView;
    @BindView(R.id.prodYearTextView)    TextView productionYearTextView;
    @BindView(R.id.machineRepairsListView) ListView repairsList;
    @BindView(R.id.detailsMachineNameTextBox) TextView machineNameTextView;

    MachineEntity machine;
    ServiceAdapter adapter;
    int machineId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_details);
        ButterKnife.bind(this);

        db = AppDatabase.getAppDatabase(getApplicationContext());
        Intent intent = getIntent();
        machineId = intent.getIntExtra("machineID",1);
        machine = db.machineDao().getMachineById(machineId);

        nameTextView.setText(machine.getName());
        odometerTextView.setText("" + db.odometerReadingDao().getMostRecentOdometerReading(machineId).getOdometerReading());
        machineNameTextView.setText(machine.getMake() + " " + machine.getModel());
        productionYearTextView.setText(new Integer(machine.getProductionDate()).toString());

    }

    @Override
    protected void onResume(){
        super.onResume();
        List<RepairEntity> machineRepairs = db.repairDao().getAllMachineRepairs(machineId);
        adapter = new ServiceAdapter(machineRepairs, getApplicationContext());
        repairsList.setAdapter(adapter);
    }


    @OnItemClick(R.id.machineRepairsListView)
    public void onRepairItemClick(View view, int position){
        long repairId = repairsList.getItemIdAtPosition(position);
        Intent intent = new Intent(MachineDetailsActivity.this, RepairDetailsActivity.class);
        intent.putExtra("repairID", repairId);
        startActivity(intent);
    }

    @OnClick(R.id.floatingActionButton)
    public void onAddServiceBtnClick(){
        Intent intent = new Intent(MachineDetailsActivity.this, AddServiceActivity.class);
        int machineID = machine.getId();
        intent.putExtra("machineID", machineID);
        startActivity(intent);
    }
}
