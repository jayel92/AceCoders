package com.example.jianlongguo.abs.UI;

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

import com.example.jianlongguo.abs.Activities.R;
import com.example.jianlongguo.abs.DB.LoginBackground;
import com.example.jianlongguo.abs.Manager.SessionManager;

public class LoginUI extends ActionBarActivity implements View.OnClickListener{

    EditText username, password;
    Button loginBut, registerBut, forgotPassBut;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());

        username= (EditText)findViewById(R.id.usernameTxt);
        password = (EditText)findViewById(R.id.passwordTxt);
        loginBut = (Button)findViewById(R.id.loginBut);
        registerBut = (Button)findViewById(R.id.registerBut);
        forgotPassBut = (Button)findViewById(R.id.forgotPassBut);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        loginBut.setOnClickListener(this);
        registerBut.setOnClickListener(this);
        forgotPassBut.setOnClickListener(this);
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
        Intent k;


        switch (v.getId()){
            case R.id.loginBut: //login button pressed
                if(!isOnline()) //check network availability
                    Toast.makeText(getBaseContext(), "No network connection! Please ensure that you have access to the Internet!", Toast.LENGTH_SHORT).show();
                else{
                    if (name.isEmpty() || pass.isEmpty()) {
                        // Display error toast
                        Toast.makeText(getBaseContext(), "Please fill in both fields!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            //create user login session
                            session.createLoginSession(name);
                            new LoginBackground(this).execute(name, pass);
                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), "Error connecting to server. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.registerBut: //register button pressed
                k = new Intent(this,RegAccBasicUI.class);
                startActivity(k);
                break;
            case R.id.forgotPassBut: //forget password button pressed
                ResetPasswordDialog dialog = new ResetPasswordDialog(this);
                dialog.show();
            default:
                break;
        }

    }
}
