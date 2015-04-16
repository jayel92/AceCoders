package com.example.jianlongguo.abs.Activities;

import android.app.DatePickerDialog.OnDateSetListener;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jianlongguo.abs.DB.NewApptBackground;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewAppt extends BaseActivity implements OnItemSelectedListener, OnClickListener{

    final static int WEEKSINADV = 8, MINWEEKS = 2, TOTALWEEKS = 52;
    Spinner clinicDD,timeSpinner,dateSpinner,typeSpinner;
    TextView apptDateTxt, apptTimeTxt, clinicLabel,typeLabel;
    EditText descTxt;
    Button apptDateBut, apptTimeBut, confirmBut, exitNewBut;
    CheckBox referralChk;
    private String[] clinicSel = {"Dental","ENT","Women Health"};
    Calendar calendar = Calendar.getInstance();
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appt);

        String jsonMyObject = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("Patient");
        }
        p1 = new Gson().fromJson(jsonMyObject, Patient.class);

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles, navMenuIcons);

        timeSpinner = (Spinner)findViewById(R.id.timeSpinner);
        typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
        //dateSpinner = (Spinner)findViewById(R.id.dateSpinner);
        descTxt = (EditText)findViewById(R.id.descTxt);
        referralChk = (CheckBox)findViewById(R.id.referralChk);
        typeLabel = (TextView)findViewById(R.id.typeLabel);
       // apptDateTxt = (TextView)findViewById(R.id.apptDateLabel);
        clinicLabel = (TextView)findViewById(R.id.clinicLabel);
        apptTimeTxt = (TextView)findViewById(R.id.apptTimeLabel);
        apptDateTxt = (TextView)findViewById(R.id.apptDateTxt);
        confirmBut = (Button)findViewById(R.id.confirmBut);
        exitNewBut = (Button)findViewById(R.id.exitNewBut);
        confirmBut.setOnClickListener(this);
        exitNewBut.setOnClickListener(this);

        clinicDD = (Spinner) findViewById(R.id.clinicDD);
        ArrayAdapter<String> adapter_clinicSel = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clinicSel);
        adapter_clinicSel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clinicDD.setAdapter(adapter_clinicSel);
        clinicDD.setOnItemSelectedListener(this);

        apptDateTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //triggers the date picker dialog
                showDatePicker();
            }
        });
    }

    public void addListenerOnSpinnerItemSelection() {
        timeSpinner = (Spinner) findViewById(R.id.timeSpinner);
        timeSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void showDatePicker(){
        DatePickerFragment date = new DatePickerFragment();

        //get current date
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

            String dateStr = (String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(year));
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date startDate = null;
            try {
                startDate = df.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = DateToCalendar(startDate);

            int curWeek = calendar.get(Calendar.WEEK_OF_YEAR);
            int curYear = calendar.get(Calendar.YEAR);
            int apptWeek = cal.get(Calendar.WEEK_OF_YEAR);

            //to allow appointments for at least 2 weeks in advance
            if ((curWeek + MINWEEKS < apptWeek && apptWeek <= curWeek + WEEKSINADV && curYear == year)
                    || (curYear < year && TOTALWEEKS - curWeek + apptWeek < WEEKSINADV)) {
                String newDateString = df.format(startDate);
                apptDateTxt.setText(newDateString);

            } else if (apptWeek > curWeek + WEEKSINADV || (curYear < year && TOTALWEEKS - curWeek + apptWeek > WEEKSINADV)) {
                Toast.makeText(getApplicationContext(), "Appointment can only be booked 2 months in advance!", Toast.LENGTH_SHORT).show();
                showDatePicker();
            } else {
                Toast.makeText(getApplicationContext(), "Appointment must be booked at least 2 weeks in advance!", Toast.LENGTH_SHORT).show();
                showDatePicker();
            }
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

        switch(R.id.list_item){
            case R.id.clinicDD:
                clinicDD.setSelection(position);
                clinicDD.getSelectedItem();
                break;
            case R.id.timeSpinner:
                timeSpinner.setSelection(position);
                timeSpinner.getSelectedItem();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String desStr = descTxt.getText().toString();
        String dateStr = apptDateTxt.getText().toString();
        String referral = null;
        String clinic = clinicDD.getSelectedItem().toString();
        String time = timeSpinner.getSelectedItem().toString();
        String type = String.valueOf(typeSpinner.getSelectedItemPosition());
        switch (v.getId()){
            case R.id.confirmBut:
                if (dateStr.equals("Appt Date"))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all fields!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (referralChk.isChecked())
                        referral = "1";
                    else
                        referral = "0";
                    try {
                        new NewApptBackground(this,p1).execute(p1.getNric(),desStr,dateStr,time,referral,type,clinic);
                        Toast.makeText(getApplicationContext(), "referral: "+referral, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        descTxt.setText(e.toString());
                    }

                }
                break;
            case R.id.exitNewBut:
                Intent e = new Intent(this,DisplayCurrAppt.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(p1);
                e.putExtra("Patient",myJson);
                startActivity(e);
                break;
            default:
                break;
        }
    }

    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}
