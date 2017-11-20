package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.models.Machine;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class AddMachine extends AppCompatActivity {
    @BindView(R.id.photoImageView) ImageView machinePhotoImgView;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 101;

    @BindView(R.id.machineNameInput) EditText machineNameInput;
    @BindView(R.id.productionYearInput) EditText productionYearInput;
    @BindView(R.id.machineMakeInput) EditText machineMakeInput;
    @BindView(R.id.modelNameInput) EditText modelNameInput;
    @BindView(R.id.odometerInput) EditText odometerInput;


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
        if(requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == RESULT_OK){
            //File photo = createPhotoFile(false);
            //Picasso.with(this).load(photo).fit().into(machinePhotoImgView);
            //uploadPhoto(photo);
        }
    }

    @OnClick(R.id.addPhotoBtn)
    public void addPhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, createPhotoFile(true));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,REQUEST_CODE_CAPTURE_IMAGE);
        }
    }

    private File createPhotoFile(boolean newPhoto) {
        //new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date());
        File photoFile = new File(Environment.getExternalStorageDirectory(), "tempMachinePhoto.jpg");
        return photoFile;
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
        Machine temp = new Machine();
        boolean obligatoryNotFilled = false;
        if(machineMakeInput != null) {
            temp.setMake(machineMakeInput.getText().toString());
        }
        if(machineNameInput != null){
            temp.setMake(machineNameInput.getText().toString());
        }
        if(productionYearInput != null){
            temp.setProductionYear(productionYearInput.getText().toString());
        }
        if(modelNameInput != null){
            temp.setModel(modelNameInput.getText().toString());
        }
        else{
            return;
        }
        if(odometerInput != null){
            temp.setOdometer(new Date(), Integer.parseInt(odometerInput.getText().toString()));
        }

    }

}
