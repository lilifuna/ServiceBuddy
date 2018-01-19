package com.example.adam.servicebuddy.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.adapters.MachineAdapter;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.entities.OdometerReadingEntity;
import com.example.adam.servicebuddy.entities.RepairEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;



public class MachinesViewActivity extends AppCompatActivity {





    @BindView(R.id.machinesList) ListView machinesList;


    List<MachineEntity> machines;
    AppDatabase db;
    MachineAdapter adapter;
    final int CONTEXT_MENU_ADD_ODOMETER_READING = 1;
    final int CONTEXT_MENU_EDIT = 2;
    final int CONTEXT_MENU_ADD_SERVICE = 3;
    final int SORT_ASCENDING = 1;
    final int SORT_DESCENDING = 0;
    final int SORT_BRAND = 0;
    final int SORT_NAME = 1;
    final int SORT_MODEL = 3;
    final int SORT_PROD_YEAR = 4;


    PopupWindow popupWindow;
    Button filterConfirmBtn, sortConfirmBtn;

    int sortOrder = SORT_ASCENDING;
    int sortField = SORT_BRAND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        AppSingleton.getInstance(getApplicationContext()).initialize(getApplicationContext());


        db = AppDatabase.getAppDatabase(getApplicationContext());
        machines = db.machineDao().getAllMachines();
        adapter = new MachineAdapter(machines , getApplicationContext());
        machinesList.setAdapter(adapter);

        registerForContextMenu(machinesList);

    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
        machinesList.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void onAddNewMachineBtnClick(){
        startActivity(new Intent(MachinesViewActivity.this, AddMachineActivity.class));
    }

    @OnItemClick(R.id.machinesList)
    void onItemClick(int position){
        Intent intent = new Intent(MachinesViewActivity.this, MachineDetailsActivity.class);
        intent.putExtra("machineID", machinesList.getItemIdAtPosition(position));
        startActivity(intent);
    }



    @Override
    public void onCreateContextMenu (ContextMenu menu, View
            v, ContextMenu.ContextMenuInfo menuInfo){

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        if(acmi.targetView.getId() == R.id.machineListRow){
            menu.setHeaderTitle("");
            menu.add(Menu.NONE, CONTEXT_MENU_ADD_ODOMETER_READING, Menu.NONE, "Add odometer reading");
            menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "Edit");
            menu.add(Menu.NONE, CONTEXT_MENU_ADD_SERVICE, Menu.NONE, "Add service");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_machines_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_filter:
                onFilterMenuOptionSelected();
                return true;

            case R.id.action_sort:
                onSortMenuOptionSelected();
                return true;

            case R.id.action_settings:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSortMenuOptionSelected(){
        CoordinatorLayout mainLayout =(CoordinatorLayout) findViewById(R.id.machinesView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popup = inflater.inflate(R.layout.popup_machines_sort, null);
        boolean focusable = true;
        popupWindow = new PopupWindow(popup,1100 , 800, focusable);
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);



        ImageView brandArrow = (ImageView) popup.findViewById(R.id.sortArrowBrand);
        ImageView nameArrow = (ImageView) popup.findViewById(R.id.sortArrowName);
        ImageView prodYearArrow = (ImageView) popup.findViewById(R.id.sortArrowProdYear);
        ImageView modelArrow = (ImageView) popup.findViewById(R.id.sortArrowModel);
        Button brandSortBtn = (Button) popup.findViewById(R.id.sortBrandBtn);
        Button nameSortBtn = (Button) popup.findViewById(R.id.sortNameBtn);
        Button prodYearSortBtn = (Button) popup.findViewById(R.id.sortProdYearBtn);
        Button modelSortBtn = (Button) popup.findViewById(R.id.sortModelBtn);

        setOrderArrowsInvisible(brandArrow, nameArrow, prodYearArrow, modelArrow);


        brandSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOrderArrowsInvisible(brandArrow, nameArrow, prodYearArrow, modelArrow);
                brandArrow.setVisibility(View.VISIBLE);
                if(sortOrder == SORT_ASCENDING){
                    brandArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    if(sortField == SORT_BRAND) sortOrder = SORT_DESCENDING;
                }
                else{
                    brandArrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    if(sortField == SORT_BRAND) sortOrder = SORT_ASCENDING;
                }
                sortField = SORT_BRAND;

            }
        });

        nameSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOrderArrowsInvisible(brandArrow, nameArrow, prodYearArrow, modelArrow);

                nameArrow.setVisibility(View.VISIBLE);
                if(sortOrder == SORT_ASCENDING){
                    nameArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    if(sortField == SORT_NAME) sortOrder = SORT_DESCENDING;
                }
                else{
                    nameArrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    if(sortField == SORT_NAME) sortOrder = SORT_ASCENDING;
                }
                sortField = SORT_NAME;
            }
        });

        modelSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOrderArrowsInvisible(brandArrow, nameArrow, prodYearArrow, modelArrow);

                modelArrow.setVisibility(View.VISIBLE);
                if(sortOrder == SORT_ASCENDING){
                    modelArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    if(sortField == SORT_MODEL) sortOrder = SORT_DESCENDING;
                }
                else{
                    modelArrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    if(sortField == SORT_MODEL) sortOrder = SORT_ASCENDING;
                }

                sortField = SORT_MODEL;
            }

        });

        prodYearSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOrderArrowsInvisible(brandArrow, nameArrow, prodYearArrow, modelArrow);

                prodYearArrow.setVisibility(View.VISIBLE);
                if(sortOrder == SORT_ASCENDING){
                    prodYearArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    if(sortField == SORT_PROD_YEAR) sortOrder = SORT_DESCENDING;
                }
                else{
                    prodYearArrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    if(sortField == SORT_PROD_YEAR) sortOrder = SORT_ASCENDING;
                }
                sortField = SORT_PROD_YEAR;
            }
        });

        sortConfirmBtn = (Button) popup.findViewById(R.id.confirmSortBtn);
        sortConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(sortField){
                    case SORT_BRAND:
                        if(sortOrder == SORT_ASCENDING) machinesList.setAdapter(new MachineAdapter(sortByBrandAscending(), getApplicationContext()));
                        else machinesList.setAdapter(new MachineAdapter(sortByBrandDescending(), getApplicationContext()));
                        break;

                    case SORT_NAME:
                        if(sortOrder == SORT_ASCENDING) machinesList.setAdapter(new MachineAdapter(sortByNameAscending(), getApplicationContext()));
                        else machinesList.setAdapter(new MachineAdapter(sortByNameDescending(), getApplicationContext()));
                        break;

                    case SORT_MODEL:
                        if(sortOrder == SORT_ASCENDING) machinesList.setAdapter(new MachineAdapter(sortByModelAscending(), getApplicationContext()));
                        else machinesList.setAdapter(new MachineAdapter(sortByModelDescending(), getApplicationContext()));
                        break;

                    case SORT_PROD_YEAR:
                        if(sortOrder == SORT_ASCENDING) machinesList.setAdapter(new MachineAdapter(sortByDateAscending(), getApplicationContext()));
                        else machinesList.setAdapter(new MachineAdapter(sortByDateDescending(), getApplicationContext()));
                        break;
                }

                popupWindow.dismiss();
            }


        });
    }

    private void setOrderArrowsInvisible(ImageView brandArrow, ImageView nameArrow, ImageView prodYearArrow, ImageView modelArrow) {
        brandArrow.setVisibility(View.INVISIBLE);
        nameArrow.setVisibility(View.INVISIBLE);
        prodYearArrow.setVisibility(View.INVISIBLE);
        modelArrow.setVisibility(View.INVISIBLE);
    }

    private void onFilterMenuOptionSelected() {
        ConstraintLayout mainLayout =(ConstraintLayout) findViewById(R.id.contentMachinesList);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popup = inflater.inflate(R.layout.machinelist_filter_popup_window, null);
        boolean focusable = true;
        popupWindow = new PopupWindow(popup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, focusable);
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
        filterConfirmBtn = (Button) popup.findViewById(R.id.filterConfirmBtn);
        filterConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterSubmit(popup);
            }
        });

        Button undoFilterBtn = (Button) popup.findViewById(R.id.undoFiltersBtn);
        undoFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterUndo();
            }
        });
    }

    private void onFilterUndo() {
        machines = db.machineDao().getAllMachines();
        adapter = new MachineAdapter(machines, this);
        machinesList.setAdapter(adapter);
        popupWindow.dismiss();
    }

    private void onFilterSubmit(View popup) {
        String filterBrandName = ((EditText)popup.findViewById(R.id.filterBrandNameEditText)).getText().toString();
        String prodYearInput = ((EditText) popup.findViewById(R.id.filterProdYearEditText)).getText().toString();
        CheckBox requiringRepairCheckBox = (CheckBox) popup.findViewById(R.id.showRequiringRepairBtn);

        int filterProdYear = 0;
        List<MachineEntity> result = db.machineDao().getAllMachines();

        if(filterBrandName.length() > 0){
            result = filterMachinesByBrand(result, filterBrandName);
        }

        if(TextUtils.isDigitsOnly(prodYearInput) && prodYearInput.length() == 4){
             filterProdYear = Integer.parseInt(prodYearInput);
             result = filterMachinesByProdYear(result, filterProdYear);
        }
        if(requiringRepairCheckBox.isChecked()){
            result = filterMachinesNeedingRepair(result);
        }

        machines = result;
        adapter = new MachineAdapter(result, this);
        machinesList.setAdapter(adapter);
        popupWindow.dismiss();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case CONTEXT_MENU_ADD_SERVICE:
                Intent intent = new Intent(MachinesViewActivity.this, AddServiceActivity.class);
                int machineID = machines.get(info.position).getId();
                intent.putExtra("machineID", machineID);
                startActivity(intent);
                return true;

            case CONTEXT_MENU_ADD_ODOMETER_READING:
                CoordinatorLayout mainLayout =(CoordinatorLayout) findViewById(R.id.machinesView);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popup = inflater.inflate(R.layout.popup_add_odometer_reading, null);
                boolean focusable = true;
                popupWindow = new PopupWindow(popup,1100 , 800, focusable);
                popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                EditText odometerInput = (EditText) popup.findViewById(R.id.odometerReadingInputEditText);
                popup.findViewById(R.id.odometerConfirmBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String rawInput =  odometerInput.getText().toString();
                        int currentReading = db.odometerReadingDao().getMostRecentOdometerReading(machines.get(info.position).getId()).getOdometerReading();
                        if(!TextUtils.isEmpty(rawInput) && TextUtils.isDigitsOnly(rawInput)){
                            int newReading = Integer.parseInt(rawInput);
                            if(newReading >= currentReading){
                                OdometerReadingEntity odometerReading = new OdometerReadingEntity();
                                odometerReading.setOdometerReading(newReading);
                                odometerReading.setMachineID(machines.get(info.position).getId());
                                odometerReading.setReadingTime(new Date());
                                db.odometerReadingDao().insertAll(odometerReading);
                                adapter = new MachineAdapter(machines,getApplicationContext());
                                machinesList.setAdapter(adapter);
                                popupWindow.dismiss();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "New reading must not be smaller than any of the previous readings", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public List<MachineEntity> sortByBrandAscending(){

        return machines =  machines.stream().sorted((o1, o2) -> o1.getMake().compareToIgnoreCase(o2.getMake())).collect(Collectors.toList());
    }

    public List<MachineEntity> sortByBrandDescending(){
        return machines = machines.stream().sorted((o1, o2) -> o2.getMake().compareToIgnoreCase(o1.getMake())).collect(Collectors.toList());
    }

    public List<MachineEntity> sortByNameDescending(){
        return machines = machines.stream().sorted((o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName())).collect(Collectors.toList());
    }

    public List<MachineEntity> sortByNameAscending(){
        return machines = machines.stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).collect(Collectors.toList());
    }

    public List<MachineEntity> sortByModelDescending(){
        return machines = machines.stream().sorted((o1, o2) -> o2.getModel().compareToIgnoreCase(o1.getModel())).collect(Collectors.toList());
    }

    public List<MachineEntity> sortByModelAscending(){
        return machines = machines.stream().sorted((o1, o2) -> o1.getModel().compareToIgnoreCase(o2.getModel())).collect(Collectors.toList());
    }

    public List<MachineEntity> sortByDateDescending(){
        return machines = machines.stream().sorted((o1, o2) -> Integer.compare(o2.getProductionDate(), o1.getProductionDate())).collect(Collectors.toList());
    }

    public List<MachineEntity> sortByDateAscending(){
        return machines = machines.stream().sorted((o1, o2) -> Integer.compare(o1.getProductionDate(), o2.getProductionDate())).collect(Collectors.toList());
    }

    public List<MachineEntity> filterMachinesNeedingRepair(List<MachineEntity> machines){
        List<MachineEntity> temp = new ArrayList<>();

        for(MachineEntity machine: machines){
            OdometerReadingEntity odometerReading = db.odometerReadingDao().getMostRecentOdometerReading(machine.getId());
            Integer mostRecentOdometerReading = 0;
            if(odometerReading != null){
                mostRecentOdometerReading = odometerReading.getOdometerReading();
            }

            for(ServicePointEntity servicePoint : db.servicePointDao().getServicePointsOfMachine(machine.getId())){
                RepairEntity lastService = db.repairDao().getLastService(servicePoint.getId());
                if(lastService == null) continue; // WHEN GIVEN SERVICE POINT HASN'T YET BEEN SERVICED

                int lastServiceOdometerReading = db.odometerReadingDao().getOdometerReadingAtTimeOfRepair(lastService.getId()).getOdometerReading();
                int hoursSinceLastService = mostRecentOdometerReading - lastServiceOdometerReading;

                if(hoursSinceLastService >= servicePoint.getInterval() ){
                    temp.add(machine);
                }
            }
        }
        machines = temp;
        return temp;
    }

    public List<MachineEntity> filterMachinesByBrand(List<MachineEntity> machines, String brand){
        List<MachineEntity> temp = new ArrayList<>();
        temp = machines.stream().filter(o -> o.getMake().contains(brand)).collect(Collectors.toList());
        machines = temp;
        return temp;
    }

    public List<MachineEntity> filterMachinesByProdYear(List<MachineEntity> machines, int prodYear){
        List<MachineEntity> temp = new ArrayList<>();
        temp = machines.stream().filter(o -> o.getProductionDate() == prodYear).collect(Collectors.toList());
        machines = temp;
        return temp;
    }


    public void showAllMachines(){
        adapter = new MachineAdapter(db.machineDao().getAllMachines(), this);
        machinesList.setAdapter(adapter);
    }


}
