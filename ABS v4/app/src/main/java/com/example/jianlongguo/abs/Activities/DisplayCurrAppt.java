package com.example.jianlongguo.abs.Activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jianlongguo.abs.Drawer.ApptAdapter;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.util.ArrayList;


public class DisplayCurrAppt extends BaseActivity implements AdapterView.OnItemClickListener,View.OnClickListener {

    TextView currentApptTxt;
    GridView currApptGrid;
    ListView list;

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    ArrayList<Appointment> apptArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_curr_appt);

        Gson gson = new Gson();
        p1 = gson.fromJson(getIntent().getStringExtra("myjson"), Patient.class);


        userid = p1.getNric();
        //Bundle b = getIntent().getExtras();
        //if (b!=null)
        //{
        //  userid = b.getString("id");
        //}

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles, navMenuIcons);

        currentApptTxt = (TextView) findViewById(R.id.currentApptTxt);
        //currApptGrid = (GridView)findViewById(R.id.currApptGrid);


        list = (ListView) findViewById(R.id.listView);
        //ArrayList<String> myArrayList = new ArrayList<String>();
        //String[] myStrArr = {"Dental:","ENT:","Women's Health:"};

        //PUT APPT INFO INTO AN ARRAY TO DISPLAY LATER
        // Appointment appt[] = ...

        //KOK CHIN, LOOK HERE!
        //KOK CHIN, LOOK HERE!
        // KOK CHIN, LOOK HERE!
        // KOK CHIN, LOOK HERE!
        // KOK CHIN, LOOK HERE!
        // KOK CHIN, LOOK HERE! Following are Temporary display.... if can put each appointment out
        //as an object then can just .add them!
        Appointment app1 = new Appointment("Dental", "1", "16-06-2015", "1500-1545");
        Appointment app2 = new Appointment("Women's Health Clinic", "3", "20-06-2015", "1100-1145");
        Appointment app3 = new Appointment("ENT", "2", "16-07-2015", "0900-0945");
        Appointment app4 = new Appointment("Dental", "1", "16-06-2015", "1500-1545");
        apptArray.add(app1);
        apptArray.add(app2);
        apptArray.add(app3);
        apptArray.add(app4);
        ApptAdapter myAdapter = new ApptAdapter(this, R.layout.appt_list, apptArray);
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
                k = new Intent(this,ApptInfoActivity.class);
                k.putExtra("myjson",myJson);
                //Intent intent1 = new Intent(this, DisplayCurrAppt.class);
                //Bundle c = new Bundle();
                //c.putString("id",userid);
                //intent1.putExtras(c);
                startActivity(k);
                finish();
    }

    private void displayView(int position) {
// update the main content by replacing fragments
        switch (position) {
            case 2:
                Intent k;
                Appointment appt = apptArray.get(position);
                Gson gson = new Gson();
                String myJson = gson.toJson(appt);
                k = new Intent(this,ManageProfile.class);
                k.putExtra("myjson",myJson);

                startActivity(k);
                finish();
                break;
        }
    }


    public Patient getPatient() {
        return p1;
    }
}