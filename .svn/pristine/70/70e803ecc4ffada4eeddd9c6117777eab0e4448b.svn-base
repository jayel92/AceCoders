package com.example.jianlongguo.abs.Activities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


public class DisplayCurrAppt extends BaseActivity implements AdapterView.OnItemClickListener{

    TextView currentApptTxt;
    GridView currApptGrid;
    ListView list;

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_curr_appt);

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles,navMenuIcons);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((TextView)view).setText("selected");
    }


}
