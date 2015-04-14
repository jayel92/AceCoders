package com.example.jianlongguo.abs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends ActionBarActivity implements View.OnClickListener{

    EditText nric, password;
    TextView regTxt;
    Button cancel, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nric = (EditText)findViewById(R.id.nricTxt);
        password = (EditText)findViewById(R.id.passwordTxt);
        cancel = (Button)findViewById(R.id.cancelBut);
        next = (Button)findViewById(R.id.nextBut);
        regTxt = (TextView)findViewById(R.id.regTxt);
        cancel.setOnClickListener(this);
        next.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
        String user = nric.getText().toString();
        String pwd = password.getText().toString();

        switch (v.getId()){
            case R.id.nextBut:
                if(user.isEmpty() || pwd.isEmpty()){
                    // Display toast
                    Toast.makeText(getApplicationContext(), "Please enter something!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(this,RegisterActivity2.class);

                    Bundle b = new Bundle();
                    b.putString("user",user);
                    b.putString("pwd",pwd);
                    i.putExtras(b);

                    startActivity(i);
                }
                break;

            case R.id.cancelBut:
                Intent k = new Intent(this,MainActivity.class);
                startActivity(k);
                break;

            default:
                break;
        }
    }
}
