package com.example.jianlongguo.abs.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jianlongguo.abs.DB.RegisterBackground;

public class RegisterActivity2 extends ActionBarActivity implements View.OnClickListener{

    EditText name, dob, email, phoneNo;
    TextView detTxt, preMocLabel;
    RadioGroup mocRadio;
    RadioButton smsRad, emailRad;
    Button cancel, register;
    CheckBox termsChk;

    String user = null;
    String pwd =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity2);

        Bundle b = getIntent().getExtras();
        if (b!=null)
        {
            user = b.getString("user");
            pwd = b.getString("pwd");
        }

        name = (EditText)findViewById(R.id.nameTxt);
        dob = (EditText)findViewById(R.id.dobTxt);
        email = (EditText)findViewById(R.id.emailLabel);
        phoneNo = (EditText)findViewById(R.id.phoneNoTxt);
        cancel = (Button)findViewById(R.id.cancelRegBut);
        register = (Button)findViewById(R.id.registerConfirmBut);
        detTxt = (TextView)findViewById(R.id.detTxt);
        preMocLabel = (TextView)findViewById(R.id.prefMocLabel);
        mocRadio = (RadioGroup)findViewById(R.id.mocRadio);
        smsRad = (RadioButton)findViewById(R.id.smsRad);
        emailRad = (RadioButton)findViewById(R.id.emailRad);
        termsChk = (CheckBox)findViewById(R.id.termsChk);

        cancel.setOnClickListener(this);
        register.setOnClickListener(this);
        smsRad.setOnClickListener(radHandler);
        email.setOnClickListener(radHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_activity2, menu);
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
    public void onClick(View v) {
        //this is what happens when you click the button
        String nameStr = name.getText().toString();
        String dobStr = dob.getText().toString();
        String emailStr = email.getText().toString();
        String phoneNoStr = phoneNo.getText().toString();
        String mode = null;
        String address = "default";
        String race = "0";


        switch (v.getId()){
            case R.id.registerConfirmBut:
                if (smsRad.isChecked())
                    mode = "0";
                else if (emailRad.isChecked())
                    mode = "1";
                else
                    Toast.makeText(getApplicationContext(), "Please choose a preferred mode of communication!", Toast.LENGTH_LONG).show();

                if (nameStr.isEmpty() || dobStr.isEmpty() || emailStr.isEmpty() || phoneNoStr.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter all fields!", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        new RegisterBackground(this).execute(user,pwd,nameStr,emailStr,address,race,phoneNoStr,mode,dobStr);

                    } catch (Exception e) {
                        name.setText(e.toString());
                    }


                }
                break;

            case R.id.cancelRegBut:
                Intent k = new Intent(this,MainActivity.class);
                startActivity(k);
                break;

            default:
                break;
        }
    }

    View.OnClickListener radHandler = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.smsRad:
                    if (smsRad.isSelected())
                        smsRad.toggle();
                    break;
                case R.id.emailRad:
                    if (emailRad.isSelected())
                        emailRad.toggle();
                    break;
            }
        }
    };
}
