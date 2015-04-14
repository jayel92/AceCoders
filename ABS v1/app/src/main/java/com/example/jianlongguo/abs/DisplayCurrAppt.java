package com.example.jianlongguo.abs;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class DisplayCurrAppt extends ActionBarActivity implements AdapterView.OnItemClickListener{

    TextView currentApptTxt;
    GridView currApptGrid;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_curr_appt);

        currentApptTxt = (TextView)findViewById(R.id.currentApptTxt);
        //currApptGrid = (GridView)findViewById(R.id.currApptGrid);

        list = (ListView)findViewById(R.id.listView);
        //ArrayList<String> myArrayList = new ArrayList<String>();
        String[] myStrArr = {"Dental Appt","ENT Appt","Women's Health Appt"};
        ArrayAdapter<String> myAdapter= new ArrayAdapter<>(this,R.layout.mylayout,R.id.textView2,
                myStrArr);
        list.setAdapter(myAdapter);
/*        myAdapter.add("a");
        myAdapter.add("z");

        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                        ((TextView)v).setText("selected");
            }
        };
        list.setOnItemClickListener(mMessageClickedHandler);*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_curr_appt, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((TextView)view).setText("selected");
    }
}
