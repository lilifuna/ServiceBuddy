package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.adapters.MachineAdapter;
import com.example.adam.servicebuddy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class MachinesViewActivity extends AppCompatActivity {

    @BindView(R.id.machinesList) ListView machinesList;


    final int CONTEXT_MENU_VIEW = 1;
    final int CONTEXT_MENU_EDIT = 2;
    final int CONTEXT_MENU_ADD_SERVICE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        AppSingleton.getInstance(getApplicationContext()).initialize(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MachinesViewActivity.this, AddMachineActivity.class));
            }
        });
        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
        MachineAdapter adapter = new MachineAdapter(db.machineDao().getAllMachines(), getApplicationContext());
        machinesList.setAdapter(adapter);

        registerForContextMenu(machinesList);

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

        menu.setHeaderTitle("");
        menu.add(Menu.NONE, CONTEXT_MENU_VIEW, Menu.NONE, "Add");
        menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "Edit");
        menu.add(Menu.NONE, CONTEXT_MENU_ADD_SERVICE, Menu.NONE, "Add service");
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

                return true;

            case R.id.action_sort:


                return true;

            case R.id.action_settings:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CONTEXT_MENU_ADD_SERVICE:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Intent intent = new Intent(MachinesViewActivity.this, AddServiceActivity.class);
                intent.putExtra("machineID", machinesList.getItemIdAtPosition(info.position));
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
