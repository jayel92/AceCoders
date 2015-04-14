package com.example.jianlongguo.abs.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    EditText username, password;
    Button loginBut, registerBut, forgotPassBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   NetworkAvailability n = new NetworkAvailability(this);

        username= (EditText)findViewById(R.id.usernameTxt);
        password = (EditText)findViewById(R.id.passwordTxt);
        loginBut = (Button)findViewById(R.id.loginBut);
        registerBut = (Button)findViewById(R.id.registerBut);
        forgotPassBut = (Button)findViewById(R.id.forgotPassBut);
        loginBut.setOnClickListener(this);
        registerBut.setOnClickListener(this);
        forgotPassBut.setOnClickListener(this);
        /*if(!this.isOnline()){
            Toast.makeText(getApplicationContext(), "No Internet connection! Please ensure that you have access to the Internet!",Toast.LENGTH_LONG).show();
            loginBut.setEnabled(false);
            registerBut.setEnabled(false);
            forgotPassBut.setEnabled(false);
        };*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
    public void onClick(View v){
        //this is what runs when you click the button
        String name = username.getText().toString();
        String pass = password.getText().toString();
        Patient p1 = new Patient();
        Intent k;
        p1.setNric(name);
        p1.setPassword(pass);

        switch (v.getId()){
            case R.id.loginBut:
                if(!isOnline())
                    Toast.makeText(getBaseContext(), "No network connection! Please ensure that you have access to the Internet!", Toast.LENGTH_SHORT).show();
                else{
                    if (name.isEmpty() || pass.isEmpty()) {
                       // Display toast
                    } else {
                    try {
                        Gson gson = new Gson();
                        String myJson = gson.toJson(p1);
                        k = new Intent(this,DisplayCurrAppt.class);
                        k.putExtra("myjson",myJson);
                        startActivity(k);
                        //new LoginBackground(this).execute(name, pass);
                            } catch (Exception e) {
                                username.setText(e.toString());
                            }
                        }
                    }
                break;

            case R.id.registerBut:
                k = new Intent(this,RegisterActivity.class);
                startActivity(k);
                break;
            case R.id.forgotPassBut:
                Toast.makeText(getApplicationContext(), "Why you forget your password!",Toast.LENGTH_SHORT).show();
            default:
                break;
        }

    }
}
