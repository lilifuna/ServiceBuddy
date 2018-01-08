package com.example.adam.servicebuddy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Adam on 2018-01-03.
 */
@Entity(tableName = "Users", indices = {@Index(value = {"login"}, unique = true)})
public class UserEntity {

    @PrimaryKey(autoGenerate = true) private int id;

    private String login;
    private String password;
    private boolean isAdmin = false;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getLogin(){
        return login;
    }

    public String getPassowrd(){
        return password;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }

    public void setIsAdmin(boolean admin){
        isAdmin = admin;
    }

}
