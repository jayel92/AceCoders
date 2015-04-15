package com.example.jianlongguo.abs.Activities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;


/**
 * Created by jianlongguo on 15/4/15.
 */
public class ChangeAppt extends BaseActivity {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    Appointment appt;
    Spinner clinicDD,timeSpinner,dateSpinner,typeSpinner;
    TextView apptDateTxt, apptTimeTxt, clinicLabel,typeLabel;
    EditText descTxt;
    Button apptDateBut, apptTimeBut, confirmBut, exitNewBut;
    CheckBox referralChk;
    DatePickerTestActivity datePickerFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appt);

        timeSpinner = (Spinner)findViewById(R.id.timeSpinner);
        typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
        //dateSpinner = (Spinner)findViewById(R.id.dateSpinner);
        descTxt = (EditText)findViewById(R.id.descTxt);
        referralChk = (CheckBox)findViewById(R.id.referralChk);
        typeLabel = (TextView)findViewById(R.id.typeLabel);
        // apptDateTxt = (TextView)findViewById(R.id.apptDateLabel);
        apptTimeTxt = (TextView)findViewById(R.id.apptTimeLabel);
        apptDateTxt = (TextView)findViewById(R.id.apptDateTxt);
        confirmBut = (Button)findViewById(R.id.confirmBut);
        exitNewBut = (Button)findViewById(R.id.exitNewBut);
        confirmBut.setOnClickListener(this);
        exitNewBut.setOnClickListener(this);

        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
       // if (extras != null) {
         //   jsonMyObject = extras.getString("Patient");
        //}
        Gson gson = new Gson();
        p1 = gson.fromJson(getIntent().getStringExtra("patient"), Patient.class);
        appt = gson.fromJson(getIntent().getStringExtra("myjson"), Appointment.class);

        String myString = appt.getTime(); //the value you want the position for
        ArrayAdapter myAdap = (ArrayAdapter) timeSpinner.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(myString);

        String typeString = appt.getType(); //the value you want the position for
        ArrayAdapter typeAdap = (ArrayAdapter) typeSpinner.getAdapter(); //cast to an ArrayAdapter
        int typeSpinnerPosition = typeAdap.getPosition(typeString);

//set the default according to value
        typeSpinner.setSelection(2);
        apptDateTxt.setText(appt.getDate());
        timeSpinner.setSelection(spinnerPosition);
        descTxt.setText(appt.getDesc());
        if (appt.getReferral().equals("1"))
            referralChk.toggle();

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles, navMenuIcons);

        apptDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //triggers the date picker dialog
                datePickerFragment.showDatePicker();
            }
        });
    }
}
