package com.example.jianlongguo.abs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class HomePage extends ActionBarActivity implements View.OnClickListener {

    ImageButton myAppt, myProfile;

    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Bundle b = getIntent().getExtras();
        if (b!=null)
        {
            id = b.getString("id");
        }

        myAppt = (ImageButton)findViewById(R.id.apptBut);
        myProfile = (ImageButton)findViewById(R.id.profileBut);
        myAppt.setOnClickListener(this);
        myProfile.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.logout:
                Intent u = new Intent(this,MainActivity.class);
                Toast.makeText(getApplicationContext(), "Logout successful!", Toast.LENGTH_SHORT).show();
                startActivity(u);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Intent j = new Intent();
        Bundle bun = new Bundle();
        bun.putString("id",id);
        j.putExtras(bun);

        switch (v.getId()){
            case R.id.apptBut:
                j.setClass(this,ManageAppt.class);
                startActivity(j);
                break;
            case R.id.profileBut:
                j.setClass(this,ManageProfile.class);
                startActivity(j);
                break;
            default:
                break;
        }
    }
}
