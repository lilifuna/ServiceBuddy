package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.MachineEntity;
import com.example.adam.servicebuddy.models.Machine;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class AddMachineActivity extends AppCompatActivity {
    @BindView(R.id.photoImageView) ImageView machinePhotoImgView;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 101;

    @BindView(R.id.machineNameInput) EditText machineNameInput;
    @BindView(R.id.productionYearInput) EditText productionYearInput;
    @BindView(R.id.machineMakeInput) EditText machineMakeInput;
    @BindView(R.id.modelNameInput) EditText modelNameInput;
    @BindView(R.id.odometerInput) EditText odometerInput;
    String mCurrentPhotoPath;

    int machineId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);
        ButterKnife.bind(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //machineId = getMachineId  TODO api
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
        finish();
    }

}
