package com.example.adam.servicebuddy.Controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.adam.servicebuddy.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class AddMachine extends AppCompatActivity {
    @BindView(R.id.photoImageView) ImageView machinePhotoImgView;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);
        ButterKnife.bind(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == RESULT_OK){
            Picasso.with(this).load(getPhotoUri(false)).fit().into(machinePhotoImgView);
        }

      /*  else if(requestCode == REQUEST_CODE_PHOTO_GALLERY && resultCode == RESULT_OK){
            Uri photoImageUri =  data.getData();
            imageView.setImageURI(photoImageUri);
        }*/
    }

    @OnClick(R.id.addPhotoBtn)
    public void addPhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoUri(true));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,REQUEST_CODE_CAPTURE_IMAGE);
        }
    }





    private Uri getPhotoUri(boolean newPhoto) {
        //new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date());
        File photoFile = new File(Environment.getExternalStorageDirectory(), "tempMachinePhoto.jpg");



        return Uri.fromFile(photoFile);
    }
}
