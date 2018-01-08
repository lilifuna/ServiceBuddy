package com.example.adam.servicebuddy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adam.servicebuddy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Adam on 2018-01-08.
 */

public class InavailableFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inavailable, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.loginFragmentBtn)
    public void startLoginActivity(){
        startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
    }
}
