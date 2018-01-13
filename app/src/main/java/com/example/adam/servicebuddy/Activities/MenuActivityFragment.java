package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adam.servicebuddy.AppSingleton;
import com.example.adam.servicebuddy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuActivityFragment extends Fragment {

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
        AppSingleton.getInstance(getContext()).logOut();
        getActivity().recreate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
