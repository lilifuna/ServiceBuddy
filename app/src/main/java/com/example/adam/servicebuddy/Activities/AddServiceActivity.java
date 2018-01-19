package com.example.adam.servicebuddy.Activities;

import android.app.IntentService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.adapters.PointServicedAdapter;
import com.example.adam.servicebuddy.entities.OdometerReadingEntity;
import com.example.adam.servicebuddy.entities.PointServicedEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class AddServiceActivity extends AppCompatActivity {

    @BindView(R.id.availableServicePointsListView) ListView availablePointsListView;
    @BindView(R.id.odometerEditText)
    EditText odometerInput;
    AppDatabase db;
    PointServicedAdapter adapter;
    int machineID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        ButterKnife.bind(this);


        db = AppDatabase.getAppDatabase(this);
        machineID = getIntent().getIntExtra("machineID", 0);
        List<ServicePointEntity> availableServicePoints = db.servicePointDao().getServicePointsOfMachine(machineID);
        adapter = new PointServicedAdapter(availableServicePoints, this);
        availablePointsListView.setAdapter(adapter);

    }

    @OnClick(R.id.submitServiceBtn)
    public void onServiceSubmit(){
        List<PointServicedEntity> pointsServiced = adapter.getPointsServiced();

        if(validateInput()){
            Toast.makeText(this, "Incorrect input", Toast.LENGTH_SHORT);
            return;
        }
        RepairEntity repairEntity = new RepairEntity();
        repairEntity.setRepairDate(new Date());
        repairEntity.setOperatorId(AppSingleton.getInstance(getApplicationContext()).getLoggedUserId());
        repairEntity.setMachineID(machineID);

        OdometerReadingEntity odometerReadingEntity = new OdometerReadingEntity();
        odometerReadingEntity.setReadingTime(new Date());
        odometerReadingEntity.setMachineID(machineID);
        odometerReadingEntity.setOdometerReading(Integer.parseInt(odometerInput.getText().toString())); // TODO: validation
        int odometerReadingId = db.odometerReadingDao().insertAll(odometerReadingEntity).get(0).intValue();

        repairEntity.setOdometerReadingId(odometerReadingId);

        int repairID = db.repairDao().insertAll(repairEntity).get(0).intValue();

        for(PointServicedEntity point : pointsServiced){
            point.setRepairId(repairID);
        }

        if(pointsServiced.size() >= 1){
            pointsServiced.stream().forEach(item -> db.pointServicedDao().insertAll(item));
        }
        finish();
    }

    private boolean validateInput(){
        if(!TextUtils.isDigitsOnly(odometerInput.getText().toString())) return false;
        if(Integer.parseInt(odometerInput.getText().toString()) < db.odometerReadingDao().getMostRecentOdometerReading(machineID).getOdometerReading()) return false;

        return true;
    }
}
