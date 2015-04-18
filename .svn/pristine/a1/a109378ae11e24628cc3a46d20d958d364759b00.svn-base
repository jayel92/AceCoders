package com.example.jianlongguo.abs.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.BaseActivity;
import com.example.jianlongguo.abs.Activities.R;
import com.example.jianlongguo.abs.DB.ProfileEditBackground;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

public class ProfileUI extends BaseActivity implements View.OnClickListener {

    Context context = this;
    TextView nricLabel, nricTxt, pwdLabel, pwdTxt, emailLabel, emailTxt, addLabel, addTxt, contactLabel, contactTxt, dobLabel, dobTxt, mocLabel;
    EditText result;
    RadioGroup mocRadio;
    RadioButton smsRadio, emailRadio;
    ImageButton changeContBut, changePwdBut, changeAddBut, changeEmailBut;
    Button saveBut;
    String id = "";
    String mode = "";

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);

        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("Patient");
        }
        p1 = new Gson().fromJson(jsonMyObject, Patient.class);

        //set up the drawer
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles,navMenuIcons);

        //instantiate all widgets on the layout
        nricLabel = (TextView) findViewById(R.id.nricLabel);
        pwdLabel = (TextView) findViewById(R.id.pwdLabel);
        emailLabel = (TextView) findViewById(R.id.emailLabel);
        addLabel = (TextView) findViewById(R.id.addLabel);
        contactLabel = (TextView) findViewById(R.id.contactLabel);
        dobLabel = (TextView) findViewById(R.id.dobLabel);


        nricTxt = (TextView) findViewById(R.id.nricTxt);
        pwdTxt = (TextView) findViewById(R.id.pwdTxt);
        emailTxt = (TextView) findViewById(R.id.emailTxt);
        addTxt = (TextView) findViewById(R.id.addTxt);
        contactTxt = (TextView) findViewById(R.id.contactTxt);
        dobTxt = (TextView) findViewById(R.id.dobTxt);
        result = (EditText) findViewById(R.id.editTextResult);
        mocRadio = (RadioGroup) findViewById(R.id.mocRadio);
        smsRadio = (RadioButton) findViewById(R.id.smsRadio);
        emailRadio = (RadioButton) findViewById(R.id.emailRadio);
        saveBut = (Button) findViewById(R.id.saveBut);

        changePwdBut = (ImageButton)findViewById(R.id.changePwdBut);
        changeContBut = (ImageButton)findViewById(R.id.changeContBut);
        changeAddBut = (ImageButton)findViewById(R.id.changeAddBut);
        changeEmailBut = (ImageButton)findViewById(R.id.changeEmailBut);

        changePwdBut.setOnClickListener(this);
        changeAddBut.setOnClickListener(this);
        changeEmailBut.setOnClickListener(this);
        changeContBut.setOnClickListener(this);
        saveBut.setOnClickListener(saveHandler);

        mocRadio.setOnClickListener(radioHandler);
        smsRadio.setOnClickListener(radioHandler);
        emailRadio.setOnClickListener(radioHandler);

        nricTxt.setText(p1.getNric());
        pwdTxt.setText(p1.getPassword());
        emailTxt.setText(p1.getEmail());
        addTxt.setText(p1.getAddress());
        contactTxt.setText(p1.getContact());
        dobTxt.setText(p1.getDob());
        if (p1.getMOC().equals("1"))
            smsRadio.toggle();
        else
            emailRadio.toggle();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_profile, menu);
        return true;
    }

    @Override
    public void onClick(final View v) {
        //gets activity_manage_profile.xml view
        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView;

        if (v.getId()==R.id.changePwdBut)
            promptsView = li.inflate(R.layout.password_prompt, null);
        else
            promptsView = li.inflate(R.layout.prompts, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        //set activity_manage_profile.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        //set dialog message
        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView enter1 = (TextView) findViewById(R.id.enterTxt);
                        EditText userInput1 = (EditText) promptsView.findViewById(R.id.userInput1);
                        TextView enter2 = (TextView) findViewById(R.id.enter2);
                        TextView enter3 = (TextView) findViewById(R.id.enter3);
                        EditText userInput2 = (EditText) promptsView.findViewById(R.id.userInput2);
                        EditText userInput3 = (EditText) promptsView.findViewById(R.id.userInput3);
                        switch (v.getId()) {
                            case R.id.changeAddBut:
                                // ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                                //       .showSoftInput(addTxt, InputMethodManager.SHOW_FORCED);
                                addTxt.setText(userInput1.getText());

                                break;
                            case R.id.changeEmailBut:
                                emailTxt.setText(userInput1.getText());
                                break;
                            case R.id.changeContBut:

                                contactTxt.setText(userInput1.getText());
                                break;
                            case R.id.changePwdBut:
                                if (userInput1.getText().toString().equals(p1.getPassword().toString())) {
                                    if (userInput2.getText().toString().equals(userInput3.getText().toString())) {
                                        pwdTxt.setText(userInput3.getText());
                                    }
                                } else
                                    Toast.makeText(context, "Please ensure your password is typed in correctly.", Toast.LENGTH_SHORT).show();

                            default:
                                break;
                        }

                    }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }

        );
        //create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        //show dialog
        alertDialog.show();
    }

    View.OnClickListener radioHandler = new View.OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                case R.id.smsRadio:
                    if (smsRadio.isSelected())
                        smsRadio.toggle();
                    break;
                case R.id.emailRadio:
                    if (emailRadio.isSelected())
                        emailRadio.toggle();
                    break;
            }
        }
    };

    View.OnClickListener saveHandler = new View.OnClickListener(){
        public void onClick(View v){
            String password = pwdTxt.getText().toString();
            String email = emailTxt.getText().toString();
            String address = addTxt.getText().toString();
            String contact = contactTxt.getText().toString();
            if (smsRadio.isChecked())
                mode = "1";
            else if (emailRadio.isChecked())
                mode = "0";

            new ProfileEditBackground(context,p1,password,email,address,contact,mode).execute();



        }
    };
}
