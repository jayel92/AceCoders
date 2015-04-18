package com.example.jianlongguo.abs.UI;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;

import com.example.jianlongguo.abs.Activities.BaseActivity;
import com.example.jianlongguo.abs.Activities.R;

public class ContactUsUI extends BaseActivity {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        //set(navMenuTitles, navMenuIcons);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_us, menu);
        return true;
    }
}
