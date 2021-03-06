package com.example.jianlongguo.abs.UI;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jianlongguo.abs.Activities.BaseActivity;
import com.example.jianlongguo.abs.Activities.R;
import com.example.jianlongguo.abs.DB.AsyncResponse;
import com.example.jianlongguo.abs.DB.DisplayBackground;
import com.example.jianlongguo.abs.DB.DisplayDentalBackground;
import com.example.jianlongguo.abs.DB.DisplayWomenBackground;
import com.example.jianlongguo.abs.Drawer.ApptAdapter;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.example.jianlongguo.abs.Manager.SessionManager;
import com.google.gson.Gson;

import java.util.ArrayList;


public class DispCurrApptUI extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AsyncResponse {

    TextView currentApptTxt;
    ListView list;
    int counter;

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    ArrayList<Appointment> apptArray = new ArrayList<>();
    private Patient p1 = new Patient();
    private Appointment ent = new Appointment();
    private Appointment den = new Appointment();
    private Appointment women = new Appointment();
    private ApptAdapter myAdapter;
    public Context context;
    SessionManager session;

    void startMyTask(AsyncTask asyncTask) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            asyncTask.execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_curr_appt);
            context = getApplicationContext();

            //session class instance
            session = new SessionManager(getApplicationContext());
        //call this function to check if user is logged in
        //directs user to login page if he's not
        session.checkLogin();
            String jsonpatient = null;

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                jsonpatient = extras.getString("Patient");

            }
            p1 = new Gson().fromJson(jsonpatient, Patient.class);

            DisplayBackground taskent = new DisplayBackground(context, p1);
            taskent.delegate = this;
            startMyTask(taskent);

            DisplayDentalBackground taskden = new DisplayDentalBackground(context, p1);
            taskden.delegate = this;
            startMyTask(taskden);

            DisplayWomenBackground taskwomen = new DisplayWomenBackground(context, p1);
            taskwomen.delegate = this;
            startMyTask(taskwomen);

            //set up the drawer
            navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
            navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
            set(navMenuTitles, navMenuIcons);


            currentApptTxt = (TextView) findViewById(R.id.currentApptTxt);

            list = (ListView) findViewById(R.id.listView);
            myAdapter = new ApptAdapter(this, R.layout.appt_list, apptArray);
            list.setAdapter(myAdapter);
            list.setItemsCanFocus(true);
            AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    goNext(position);
                }
            };

            list.setOnItemClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_curr_appt, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) view).setText("selected");
    }

    private void goNext(int position) {
    // update the main content by replacing fragments
        Intent k;
        Appointment appt = apptArray.get(position);
        Gson gson = new Gson();
        String myJson = gson.toJson(appt);
        String pat = gson.toJson(p1);
        k = new Intent(this, ApptInfoUI.class);
        k.putExtra("myjson", myJson);
        k.putExtra("patient", pat);
        startActivity(k);
        this.finish();
    }

    public void processFinish(Appointment result) {
        if (result.getClinic() != null) {
            if (result.getClinic() == "ENT")
                this.ent = result;
            else if (result.getClinic() == "Dental")
                this.den = result;
            else if (result.getClinic() == "Women Health")
                this.women = result;

            apptArray.add(result);

            myAdapter.notifyDataSetChanged();
        }
        counter++;
        if (counter == 3)
            if (apptArray.isEmpty()) {
                currentApptTxt.setText("You have no scheduled appointment yet.");
            }
            else {
                currentApptTxt.setText("Your upcoming appointments:");
            }
    }


    public Patient getPatient() {
        return p1;
    }

}