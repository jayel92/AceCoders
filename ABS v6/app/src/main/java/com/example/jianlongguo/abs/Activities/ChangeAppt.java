package com.example.jianlongguo.abs.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jianlongguo.abs.DB.ChangeApptBackground;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by jianlongguo on 15/4/15.
 */
public class ChangeAppt extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    Calendar calendar;
    final static int WEEKSINADV = 8, MINWEEKS = 2, TOTALWEEKS = 52;
    Appointment appt;
    Spinner clinicDD, timeSpinner, dateSpinner, typeSpinner;
    TextView apptDateTxt, apptTimeTxt, clinicLabel, typeLabel;
    EditText descTxt;
    Button apptDateBut, apptTimeBut, confirmBut, exitNewBut;
    CheckBox referralChk;
    Patient p1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appt);

        timeSpinner = (Spinner) findViewById(R.id.timeSpinner);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        //dateSpinner = (Spinner)findViewById(R.id.dateSpinner);
        descTxt = (EditText) findViewById(R.id.descTxt);
        referralChk = (CheckBox) findViewById(R.id.referralChk);
        typeLabel = (TextView) findViewById(R.id.typeLabel);
        // apptDateTxt = (TextView)findViewById(R.id.apptDateLabel);
        apptTimeTxt = (TextView) findViewById(R.id.apptTimeLabel);
        apptDateTxt = (TextView) findViewById(R.id.apptDateTxt);
        confirmBut = (Button) findViewById(R.id.confirmBut);
        exitNewBut = (Button) findViewById(R.id.exitNewBut);
        confirmBut.setOnClickListener(this);
        exitNewBut.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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

        String typeString = appt.getType(); //the value you want the position for
        ArrayAdapter typeAdap = (ArrayAdapter) typeSpinner.getAdapter(); //cast to an ArrayAdapter

//set the default according to value
        for (int i=0;i<4;i++) {
            if (typeSpinner.getItemAtPosition(i).toString().equals(appt.getType())){
                typeSpinner.setSelection(i);
                break;
            }
            Toast.makeText(getApplicationContext(), typeSpinner.getItemAtPosition(i).toString() + " "+appt.getType()+" ", Toast.LENGTH_LONG).show();
        }
        apptDateTxt.setText(appt.getDate());

        timeSpinner.setPrompt(appt.getTime());
      //  timeSpinner.setSelection(spinnerPosition);
        descTxt.setText(appt.getDesc());
        if (appt.getReferral().equals("1"))
            referralChk.toggle();

        apptDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //triggers the date picker dialog
                showDatePicker();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appt_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.logout:
                createLogoutDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to proceed without saving the changes?");
        alertDlg.setCancelable(false); // We avoid that the dialog can be cancelled, forcing the user to choose one of the options

        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //ChangeAppt.super.onBackPressed();

                finish();
                Intent k = new Intent(getApplicationContext(),DisplayCurrAppt.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(appt);
                String pat = gson.toJson(p1);
                k.putExtra("myjson", myJson);
                k.putExtra("patient", pat);
                startActivity(k);

            }
        });

        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // We do nothing
            }
        });
        alertDlg.create().show();
        onPause();
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

    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            String dateStr = (String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(year));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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

    public void createLogoutDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to logout?");
        alertDlg.setCancelable(false); // We avoid that the dialong can be cancelled, forcing the user to choose one of the options

        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //clears history
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Logout successful!", Toast.LENGTH_SHORT).show();
            }
        });

        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // We do nothing
            }
        });
        alertDlg.create().show();
        onPause();
    }

    @Override
    public void onClick(View v) {
        String desStr = descTxt.getText().toString();
        String dateStr = apptDateTxt.getText().toString();
        String referral = null;
        String time = timeSpinner.getSelectedItem().toString();
        String type = String.valueOf(typeSpinner.getSelectedItemPosition());

        Intent k = new Intent(getApplicationContext(),DisplayCurrAppt.class);
        Gson gson = new Gson();
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
                        ChangeApptBackground change = new ChangeApptBackground(this,p1,appt);
                        change.execute(p1.getNric(), desStr, dateStr, time, referral, type);

                    } catch (Exception e) {
                        descTxt.setText(e.toString());
                    }

                }

                //String myJson = gson.toJson(appt);
                String pat = gson.toJson(p1);
                //k.putExtra("myjson", myJson);
                k.putExtra("patient", pat);
                startActivity(k);
                break;
            case R.id.exitNewBut:
                String myJson = gson.toJson(p1);
                k.putExtra("Patient",myJson);
                startActivity(k);
                break;
            default:
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(R.id.list_item){
            case R.id.timeSpinner:
                timeSpinner.setSelection(position);
                timeSpinner.getSelectedItem();
                break;
        }
    }

    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
