package com.example.jianlongguo.abs.Activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jianlongguo.abs.DB.DisplayBackground;
import com.example.jianlongguo.abs.DB.DisplayDentalBackground;
import com.example.jianlongguo.abs.DB.DisplayWomenBackground;
import com.example.jianlongguo.abs.Drawer.ApptAdapter;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.util.ArrayList;


public class DisplayCurrAppt extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AsyncResponse {

    TextView currentApptTxt, nilAppt;
    GridView currApptGrid;
    ListView list;

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    ArrayList<Appointment> apptArray = new ArrayList<>();
    private Patient p1 = new Patient();
    private Appointment ent = new Appointment();
    private Appointment den = new Appointment();
    private Appointment women = new Appointment();
    private ApptAdapter myAdapter;


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

        nilAppt = (TextView) findViewById(R.id.nilAppt);
        currentApptTxt = (TextView) findViewById(R.id.currentApptTxt);

        if (apptArray == null){
            Toast.makeText(getApplicationContext(), "Please enter all fields!", Toast.LENGTH_LONG).show();

            nilAppt.setText("You have no scheduled appointment");
        }

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
        //Patient p1 = new Patient();
        Appointment appt = apptArray.get(position);
        Gson gson = new Gson();
        String myJson = gson.toJson(appt);
        String pat = gson.toJson(p1);
        k = new Intent(this, ApptInfoActivity.class);
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
            Toast.makeText(getApplicationContext(),result.toString(), Toast.LENGTH_LONG).show();

            myAdapter.notifyDataSetChanged();
        }

    }


    public Patient getPatient() {
        return p1;
    }


}