package com.example.jianlongguo.abs.Activities;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jianlongguo.abs.DB.ProfileBackground;
import com.example.jianlongguo.abs.DB.ProfileEditBackground;

public class ManageProfile extends BaseActivity implements View.OnClickListener {

    Context context = this;
    TextView nricLabel, nricTxt, pwdLabel, pwdTxt, emailLabel, emailTxt, addLabel, addTxt, contactLabel, contactTxt, dobLabel, dobTxt, mocLabel;
    EditText result;
    RadioGroup mocRadio;
    RadioButton smsRadio, emailRadio;
    Button changeBut, changeAddBut, changeConBut, saveBut;
    String id = "";
    String mode = "";

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
        set(navMenuTitles,navMenuIcons);

        Bundle b = getIntent().getExtras();
        if (b!=null)
        {
            id = b.getString("id");
        }

        nricLabel = (TextView) findViewById(R.id.nricLabel);
        nricTxt = (TextView) findViewById(R.id.nricTxt);
        pwdLabel = (TextView) findViewById(R.id.pwdLabel);
        pwdTxt = (TextView) findViewById(R.id.pwdTxt);
        emailLabel = (TextView) findViewById(R.id.emailLabel);
        emailTxt = (TextView) findViewById(R.id.emailTxt);
        addLabel = (TextView) findViewById(R.id.addLabel);
        addTxt = (TextView) findViewById(R.id.addTxt);
        contactLabel = (TextView) findViewById(R.id.contactLabel);
        contactTxt = (TextView) findViewById(R.id.contactTxt);
        dobLabel = (TextView) findViewById(R.id.dobLabel);
        dobTxt = (TextView) findViewById(R.id.dobTxt);
        mocLabel = (TextView) findViewById(R.id.mocLabel);
        changeBut = (Button) findViewById(R.id.changeEmailBut);
        result = (EditText) findViewById(R.id.editTextResult);
        mocRadio = (RadioGroup) findViewById(R.id.mocRadio);
        smsRadio = (RadioButton) findViewById(R.id.smsRadio);
        emailRadio = (RadioButton) findViewById(R.id.emailRadio);
        changeAddBut = (Button) findViewById(R.id.changeAddBut);
        changeConBut = (Button) findViewById(R.id.changeConBut);
        saveBut = (Button) findViewById(R.id.saveBut);

        changeBut.setOnClickListener(this);
        changeAddBut.setOnClickListener(this);
        changeConBut.setOnClickListener(this);
        mocRadio.setOnClickListener(radioHandler);
        smsRadio.setOnClickListener(radioHandler);
        emailRadio.setOnClickListener(radioHandler);
        saveBut.setOnClickListener(saveHandler);

        new ProfileBackground(this,nricTxt,pwdTxt,emailTxt,addTxt,contactTxt,dobTxt).execute(id);

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
        View promptsView = li.inflate(R.layout.prompts, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        //set activity_manage_profile.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        //set dialog message
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (v.getId()) {
                            case R.id.changeAddBut:
                                addTxt.setText(userInput.getText());
                                break;
                            case R.id.changeEmailBut:
                                emailTxt.setText(userInput.getText());
                                break;
                            case R.id.changeConBut:
                                contactTxt.setText(userInput.getText());
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
            String email = emailTxt.getText().toString();
            String address = addTxt.getText().toString();
            String contact = contactTxt.getText().toString();
            if (smsRadio.isChecked())
                mode = "0";
            else if (emailRadio.isChecked())
                mode = "1";
            new ProfileEditBackground(context).execute(id,email,address,contact,mode);
        }
    };
}

