package com.example.jianlongguo.abs.Activities;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

/**
 * Created by jianlongguo on 15/4/15.
 */
public class ChangeAppt extends NewAppt {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    Appointment appt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appt);

        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
       // if (extras != null) {
         //   jsonMyObject = extras.getString("Patient");
        //}
        Gson gson = new Gson();
        p1 = gson.fromJson(getIntent().getStringExtra("patient"), Patient.class);
        appt = gson.fromJson(getIntent().getStringExtra("myjson"), Appointment.class);

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles, navMenuIcons);

        clinicLabel.setText(appt.getDate());
        typeLabel.setText(p1.getNric());
    }
}
