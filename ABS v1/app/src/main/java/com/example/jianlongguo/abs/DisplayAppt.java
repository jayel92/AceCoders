package com.example.jianlongguo.abs;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class DisplayAppt extends ActionBarActivity implements View.OnClickListener{

    Button currentApptBut, pastApptBut;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_appt);

        Bundle b = getIntent().getExtras();
        if (b!=null)
        {
            id = b.getString("id");
        }

        currentApptBut = (Button)findViewById(R.id.currentApptBut);
        pastApptBut = (Button)findViewById(R.id.pastApptBut);
        currentApptBut.setOnClickListener(this);
        pastApptBut.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_appt, menu);
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
        Intent q = new Intent();
        Bundle bun = new Bundle();
        bun.putString("id",id);
        q.putExtras(bun);
        switch (v.getId()){
            case R.id.currentApptBut:
                q.setClass(this,DisplayCurrAppt.class);
                startActivity(q);
                break;
            case R.id.pastApptBut:
                q.setClass(this,DisplayPastAppt.class);
                startActivity(q);
                break;
            default:
                break;
        }
    }
}
