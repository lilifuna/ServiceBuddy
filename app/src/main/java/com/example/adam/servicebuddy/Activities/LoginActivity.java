package com.example.adam.servicebuddy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.Daos.UserDao;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.loginInput) EditText loginInput;
    @BindView(R.id.passwordInput) EditText passwordInput;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        db = AppDatabase.getAppDatabase(getApplicationContext());
        UserEntity user1 = new UserEntity();

        //TEST DATA
        user1.setLogin("lilifuna");
        user1.setPassword("a11234");
        user1.setIsAdmin(true);
        user1.setName("Adam Kokot");
        db.userDao().insertAll(user1);
    }


    @OnClick(R.id.loginBtn)
    public void attemptLogin(){
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();
        //if(!checkIfPasswordIsValid(password)) return;
        //if(!checkIfUsernameIsValid(login)) return;
        if(authenticate(login, password)){
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
        }
        else{
            Toast.makeText(this, "Wrong login and/or password", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean authenticate(String login, String password){

        UserEntity user = db.userDao().findByName(login);
        if(user != null){
            if(user.getPassword().equals(password)) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("SessionData", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("UserId",user.getId());
                editor.apply();
                return true;
            }
        }

        return false;
    }

    private boolean checkIfUsernameIsValid(String username){
        if(username.isEmpty()) return false;

        return true;
    }

    private boolean checkIfPasswordIsValid(String password){
        if(password.length() <= 5) return false;

        return true;
    }
}
