package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewUserActivity extends AppCompatActivity {

    @BindView(R.id.newUserLoginInput)
    EditText loginInput;

    @BindView(R.id.newUserPasswordInput) EditText passwordInput;
    @BindView(R.id.newUserNameEditText) EditText nameInput;
    @BindView(R.id.adminSwitch)
    Switch adminSwitch;

    AppDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);
        db = AppDatabase.getAppDatabase(getApplicationContext());
    }


    @OnClick(R.id.confirmBtn)
    public void registerNewUser(){
        UserEntity newUser = new UserEntity();
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();
        String name = nameInput.getText().toString();
        boolean isAdmin = adminSwitch.isChecked();

        if(login.length()>=0 && password.length() >= 5 && name.length() >= 0){
            newUser.setLogin(login);
            newUser.setPassword(password);
            newUser.setIsAdmin(isAdmin);
            newUser.setName(name);
            db.userDao().insertAll(newUser);
            startActivity(new Intent(NewUserActivity.this, UserListActivity.class));
        }
        else{
            Toast.makeText(this, "Login, password or name too short!", Toast.LENGTH_SHORT).show();
        }

    }


}
