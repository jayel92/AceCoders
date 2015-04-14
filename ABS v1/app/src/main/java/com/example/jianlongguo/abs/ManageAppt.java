package com.example.jianlongguo.abs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ManageAppt extends ActionBarActivity implements View.OnClickListener{

    Button display, newAppt;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appt);

        Bundle b = getIntent().getExtras();
        if (b!=null)
        {
            id = b.getString("id");
        }

        display = (Button)findViewById(R.id.displayBut);
        newAppt = (Button)findViewById(R.id.newBut);
        display.setOnClickListener(this);
        newAppt.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_appt, menu);
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
        //this is what happens when you click the button
        switch(v.getId()){
            case R.id.displayBut:
                Intent j = new Intent(this,DisplayAppt.class);
                Bundle bun = new Bundle();
                bun.putString("id",id);
                j.putExtras(bun);
                startActivity(j);
                break;
            case R.id.newBut:
                Intent p = new Intent(this,NewAppt.class);
                Bundle bun2 = new Bundle();
                bun2.putString("id",id);
                p.putExtras(bun2);
                startActivity(p);
                break;
            default:
                break;
        }
    }
}
