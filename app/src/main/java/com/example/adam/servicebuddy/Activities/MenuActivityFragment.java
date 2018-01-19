package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adam.servicebuddy.AppDatabase;
import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuActivityFragment extends Fragment {

   @BindView(R.id.userListBtn)
   Button userListBtn;

    AppDatabase db;

    public MenuActivityFragment() {


    }

    @OnClick(R.id.machineListBtn)
    public void startMachineListActivity(){
       startActivity(new Intent(getActivity(), MachinesViewActivity.class));
    }

    @OnClick(R.id.userListBtn)
    public void startUserListActivity(){
        startActivity(new Intent(getActivity(), UserListActivity.class));
    }

    @OnClick(R.id.logoutBtn)
    public void onLogoutBtnClick(){
        AppSingleton.getInstance(getActivity().getApplicationContext()).logOut();
        getActivity().recreate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        db = AppDatabase.getAppDatabase(getActivity().getApplicationContext());
        int loggedUserId = AppSingleton.getInstance(getActivity().getApplicationContext()).getLoggedUserId();
        userListBtn.setEnabled(false);
        if(loggedUserId != -1){
            boolean isAdmin = db.userDao().findById(loggedUserId).getIsAdmin();
            if(isAdmin) userListBtn.setEnabled(true);
        }

        return view;
    }
}
