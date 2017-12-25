package com.example.user.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.myapplication.fragments_schedule.Schedule.FragmentFriday;
import com.example.user.myapplication.fragments_schedule.Schedule.FragmentModnay;
import com.example.user.myapplication.fragments_schedule.Schedule.FragmentThuersday;
import com.example.user.myapplication.fragments_schedule.Schedule.FragmentTuesday;
import com.example.user.myapplication.fragments_schedule.Schedule.FragmentWednesday;


public class FragmentMain extends Fragment implements View.OnClickListener{
    private String title;
    private int page;
    public Button bMonday;
    public Button bTuesday;
    public Button bWednesday;
    public Button bThuersday;
    public Button bFriday;

    Fragment childFragment = new FragmentModnay();


    public static FragmentMain newInstance(int page, String title) {
        FragmentMain fragmentSchedule = new FragmentMain();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentSchedule.setArguments(args);
        return fragmentSchedule;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_schedule, container, false);
        bMonday = (Button) view.findViewById(R.id.bMonday);
        bMonday.setOnClickListener(this);
        bTuesday = (Button)view.findViewById(R.id.bTuesday);
        bTuesday.setOnClickListener(this);
        bWednesday=(Button)view.findViewById(R.id.bWednesday);
        bWednesday.setOnClickListener(this);
        bThuersday=(Button)view.findViewById(R.id.bThuersday);
        bThuersday.setOnClickListener(this);
        bFriday=(Button)view.findViewById(R.id.bFriday);
        bFriday.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bMonday:
                childFragment = new FragmentModnay();
                break;
            case R.id.bTuesday:
                childFragment = new FragmentTuesday();
                break;
            case R.id.bWednesday:
                childFragment = new FragmentWednesday();
            case R.id.bThuersday:
                childFragment  = new FragmentThuersday();
            case R.id.bFriday:
                childFragment = new FragmentFriday();
        }
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.flSchedule, childFragment).commit();


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

      //  insertNestedFragment();
    }
    /*
public void onChange (View view){
    Fragment childFragment = new FragmentModnay();

    switch (view.getId()){
        case R.id.bMonday:
            childFragment = new FragmentModnay();
            break;
        case R.id.bTuesday:
            childFragment = new FragmentTuesday();
            break;
    }
    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
    transaction.replace(R.id.flSchedule, childFragment).commit();
}*/
    private void insertNestedFragment() {


        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.flSchedule, childFragment).commit();


    }



}