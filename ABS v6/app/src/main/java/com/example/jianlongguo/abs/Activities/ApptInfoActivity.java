package com.example.jianlongguo.abs.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

public class ApptInfoActivity extends BaseActivity implements View.OnClickListener {

    TextView clinicLabel, typeLabel, locLabel, timeLabel, dateLabel, remarksLabel, remarksTxt;
    ImageButton changeApptBut, cancelApptBut;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    Appointment appt;
    Patient p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_info);


        Gson gson = new Gson();
        appt = gson.fromJson(getIntent().getStringExtra("myjson"), Appointment.class);
        p1 = gson.fromJson(getIntent().getStringExtra("patient"), Patient.class);

        clinicLabel = (TextView) findViewById(R.id.clinicLabel);
        typeLabel = (TextView) findViewById(R.id.typeLabel);
        locLabel = (TextView) findViewById(R.id.locLabel);
        dateLabel = (TextView) findViewById(R.id.timeLabel);
        timeLabel = (TextView) findViewById(R.id.dateLabel);
        remarksLabel = (TextView) findViewById(R.id.remarksLabel);
        remarksTxt = (TextView) findViewById(R.id.remarksTxt);
        changeApptBut = (ImageButton) findViewById(R.id.changeApptBut);
        cancelApptBut = (ImageButton) findViewById(R.id.cancelApptBut);

        changeApptBut.setOnClickListener(this);
        cancelApptBut.setOnClickListener(this);
        //set up the drawer
        // navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        //navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        //set(navMenuTitles, navMenuIcons);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        typeLabel.setText(Html.fromHtml("<h3>" + appt.getType() + "</h3>"));
        clinicLabel.setText(Html.fromHtml("<b>" + "Clinic: " + "</b>" + "<i>" + appt.getClinic() + "</i>"));
        dateLabel.setText(Html.fromHtml("<b>" + "Date: " + "</b>" + "<i>" + appt.getDate() + "</i>"));
        timeLabel.setText(Html.fromHtml("<b>" + "Time: " + "</b>" + "<i>" + appt.getTime() + "hrs" + "</i>"));

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

        switch (id) {
            case R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                super.createLogoutDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelApptBut:
                CancelApptDialog dia = new CancelApptDialog(this, p1, appt);
                dia.show();
                break;
            case R.id.changeApptBut:
                Intent k;
                Gson gson = new Gson();
                String myJson = gson.toJson(appt);
                String pat = gson.toJson(p1);
                k = new Intent(this, ChangeAppt.class);
                k.putExtra("myjson", myJson);
                k.putExtra("patient", pat);
                startActivity(k);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to exit?");
        alertDlg.setCancelable(false); // We avoid that the dialog can be cancelled, forcing the user to choose one of the options

        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ApptInfoActivity.super.onBackPressed();
                finish();
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


}
