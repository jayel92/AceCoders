package com.example.jianlongguo.abs.Activities;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NewAppt extends BaseActivity implements OnItemSelectedListener, OnClickListener {

    Spinner clinicDD;
    TextView apptDateTxt, apptTimeTxt, clinicLabel;
    Button apptDateBut, apptTimeBut, confirmBut, exitNewBut;
    CheckBox referralChk;
    private String[] clinicSel = {"Dental","ENT","Women's Health"};
    private Date date;
    final int Date_Dialog_ID=0;
    Calendar calendar = Calendar.getInstance();
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appt);

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles, navMenuIcons);

        clinicDD = (Spinner)findViewById(R.id.clinicDD);
        ArrayAdapter<String> adapter_clinicSel = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,clinicSel);
        adapter_clinicSel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clinicDD.setAdapter(adapter_clinicSel);
        clinicDD.setOnItemSelectedListener(this);

        referralChk = (CheckBox)findViewById(R.id.referralChk);
        apptDateTxt = (TextView)findViewById(R.id.apptDateTxt);
        apptTimeTxt = (TextView)findViewById(R.id.apptTimeTxt);
        clinicLabel = (TextView)findViewById(R.id.clinicLabel);
        apptDateBut = (Button)findViewById(R.id.apptDateBut);
        apptTimeBut = (Button)findViewById(R.id.apptTimeBut);
        confirmBut = (Button)findViewById(R.id.confirmBut);
        exitNewBut = (Button)findViewById(R.id.exitNewBut);
        confirmBut.setOnClickListener(this);
        exitNewBut.setOnClickListener(this);


        apptTimeBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //triggers the time picker dialog
                showTimePicker();
            }
        });

        apptDateBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //triggers the date picker dialog
                showDatePicker();
            }
        });
    }

    private void showTimePicker() {
        TimePickerFragment time = new TimePickerFragment();

        Bundle timeargs = new Bundle();
        timeargs.putInt("Hour", calendar.get(Calendar.HOUR_OF_DAY));
        timeargs.putInt("Minute", calendar.get(Calendar.MINUTE));
        time.setArguments(timeargs);

        time.setCallBack(onTime);
        time.show(getSupportFragmentManager(), "Time Picker");
        apptTimeTxt.setText(String.valueOf(time.toString()));
    }

    OnTimeSetListener onTime = new OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            apptTimeTxt.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
        }
    };

    public void showDatePicker(){
        DatePickerFragment date = new DatePickerFragment();

        Bundle args = new Bundle();
        args.putInt("Year",calendar.get(Calendar.YEAR));
        args.putInt("Month",calendar.get(Calendar.MONTH));
        args.putInt("Day",calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        date.setCallBack(onDate);
        date.show(getSupportFragmentManager(),"Date Picker");
    }

    OnDateSetListener onDate = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //@Override
        //public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String monthStr = calendar.getDisplayName(monthOfYear, Calendar.LONG, Locale.getDefault());
            apptDateTxt.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(year));
       // }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_appt, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        clinicDD.setSelection(position);
        String selClinic = (String)clinicDD.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirmBut:
                Toast.makeText(getApplicationContext(), "Appointment created successfully.", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(this,DisplayCurrAppt.class);
                startActivity(c);
                break;
            case R.id.exitNewBut:
                Intent d = new Intent(this,ManageAppt.class);
                startActivity(d);
                break;
            default:
                break;
        }
    }
}
