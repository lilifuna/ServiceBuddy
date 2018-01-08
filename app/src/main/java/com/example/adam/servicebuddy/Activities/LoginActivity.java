package com.example.adam.servicebuddy.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

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
        user1.setIsAdmin(false);
        db.userDao().insertAll(user1);
    }


    @OnClick(R.id.loginBtn)
    public void attemptLogin(){
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();
        //if(!checkIfPasswordIsValid(password)) return;
        //if(!checkIfUsernameIsValid(login)) return;
        authenticate(login, password);
    }


    private void authenticate(String login, String password){

        UserEntity user = db.userDao().findByName(login);
        if(user != null){
            if(user.getPassword().equals(password)) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("SessionData", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("UserId",user.getId());
                editor.commit();

            }
        }


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
