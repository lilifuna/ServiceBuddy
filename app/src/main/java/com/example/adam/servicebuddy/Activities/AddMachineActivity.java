package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.cloudinary.utils.ObjectUtils;
import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.adapters.PointServicedAdapter;
import com.example.adam.servicebuddy.adapters.ServicePointAddAdapter;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.entities.ServicePointEntity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class AddMachineActivity extends AppCompatActivity {

    @BindView(R.id.photoImageView) ImageView machinePhotoImgView;
    @BindView(R.id.machineServicePointsToAddListView) ListView servicePointsListView;
    @BindView(R.id.machineNameInput) EditText machineNameInput;
    @BindView(R.id.productionYearInput) EditText productionYearInput;
    @BindView(R.id.machineMakeInput) EditText machineMakeInput;
    @BindView(R.id.modelNameInput) EditText modelNameInput;
    @BindView(R.id.odometerInput) EditText odometerInput;

    String mCurrentPhotoPath;
    ServicePointAddAdapter adapter;
    PopupWindow popupWindow;
    ArrayList<String> servicePoints = new ArrayList<>();
    int machineId = 0;
    Button submitNewServicePointBtn;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);
        ButterKnife.bind(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        String[] defaultServicePoints = getResources().getStringArray(R.array.default_service_points);
        servicePoints.addAll(Arrays.asList(defaultServicePoints));

        adapter = new ServicePointAddAdapter(servicePoints, this);
        servicePointsListView.setAdapter(adapter);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            Picasso.with(this).load(new File(mCurrentPhotoPath)).fit().centerCrop().into(machinePhotoImgView);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @OnClick(R.id.addPhotoBtn)
    public void addPhoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CODE_CAPTURE_IMAGE);
            }
        }
    }


    @OnClick(R.id.customServicePointButton)
    public void onCustomServicePointBtnClick(){
        ConstraintLayout mainLayout =(ConstraintLayout) findViewById(R.id.activity_add_machine_layout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popup = inflater.inflate(R.layout.custom_servicepoint_popup_window, null);
        boolean focusable = true;
        popupWindow = new PopupWindow(popup,1100 , 800, focusable);
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
        submitNewServicePointBtn = (Button) popup.findViewById(R.id.submitCustomServicePointBtn);
        submitNewServicePointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPointName = ((EditText) popup.findViewById(R.id.customServicePointNameEditText)).getText().toString();
                popupWindow.dismiss();
                servicePoints.add(newPointName);
                adapter.notifyDataSetChanged();
            }
        });
    }





    private  String uploadPhoto(File photo){
        String name  = machineId + "main_photo";
        try {
           AppSingleton.cloud.uploader().upload(photo.getAbsolutePath(), ObjectUtils.asMap("public_id", "name"));
        }
        catch(IOException ex){
            //TODO
        }
        return "http://res.cloudinary.com/servicebuddy/image/upload/" + name + ".jpg";
    }



    @OnClick(R.id.submitBtn)
    public void submitMachine(){
        MachineEntity machine;
        boolean obligatoryNotFilled = false;
        if(machineMakeInput != null) {
            //temp.setMake(machineMakeInput.getText().toString());
        }
        else return;

        if(machineNameInput != null){
            //temp.setMake(machineNameInput.getText().toString());
        }
        else return;

        if(productionYearInput != null){
           // temp.setProductionYear(productionYearInput.getText().toString());
        }
        else return;

        if(modelNameInput != null){
            //temp.setModel(modelNameInput.getText().toString());
        }
        else return;


        if(odometerInput != null){
            //temp.setOdometer(new Date(), Integer.parseInt(odometerInput.getText().toString()));
        }
        else return;

        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
        machine = new MachineEntity(machineNameInput.getText().toString(), machineMakeInput.getText().toString(), Integer.parseInt(productionYearInput.getText().toString()));
        db.machineDao().insertAll(machine);
        int machineId = machine.getId();
        ServicePointEntity[] entitiesToAdd = adapter.getEntitiesToCreate(machineId);
        db.servicePointDao().insertAll(entitiesToAdd);

        finish();
    }

}
