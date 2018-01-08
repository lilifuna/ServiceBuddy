package com.example.adam.servicebuddy.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.R;
import com.example.adam.servicebuddy.entities.UserEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserListActivity extends AppCompatActivity {

    AppDatabase db;

    ListView userList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
        db = AppDatabase.getAppDatabase(getApplicationContext());

        List<UserEntity> allUsers = db.userDao().getAllUsers();
        adapter = new UserAdapter(allUsers,UserListActivity.this);
        userList = (ListView) findViewById(R.id.userList);
        userList.setAdapter(adapter);
    }


    @OnClick(R.id.userAddBtn)
    public void openUserAddActivity(){
        startActivity(new Intent(UserListActivity.this, NewUserActivity.class));
    }

    private void createDummyData(){
        UserEntity user1 = new UserEntity(), user2 = new UserEntity();

        //TEST DATA
        user1.setLogin("adomik");
        user1.setPassword("a11234");
        user1.setIsAdmin(false);


        user2.setLogin("adoras5");
        user2.setPassword("a11234");
        user2.setIsAdmin(false);
        db.userDao().insertAll(user1, user2);
    }

    private class UserAdapter extends BaseAdapter {

        Context context;
        List<UserEntity> users;
        LayoutInflater inflater;

        public UserAdapter(List<UserEntity> users, Context context){
            this.users = users;

            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return users.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            MyViewHolder mViewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.user_list_row, parent, false);
                mViewHolder = new MyViewHolder(convertView);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (MyViewHolder) convertView.getTag();
            }

            UserEntity currentListData = (UserEntity) getItem(position);

            mViewHolder.nameTextView.setText(currentListData.getLogin());
            Picasso.with(context).load("https://i.pinimg.com/736x/f4/bf/92/f4bf92d41c99d853914f703e968647cd--emoji-faces-eyes-emoji.jpg")
                    .into(mViewHolder.photoImageView);


            return convertView;
        }


    }
    private class MyViewHolder {
        TextView nameTextView;
        ImageView photoImageView;


        public MyViewHolder(View item) {
            nameTextView = (TextView) item.findViewById(R.id.username);
            photoImageView = (ImageView) item.findViewById(R.id.userPhoto);

        }
    }
}
