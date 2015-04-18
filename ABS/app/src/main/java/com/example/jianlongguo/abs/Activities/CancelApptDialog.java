package com.example.jianlongguo.abs.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.jianlongguo.abs.DB.DeleteBackground;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;

/**
 * Created by jianlongguo on 15/4/15.
 */
public class CancelApptDialog extends Dialog implements View.OnClickListener{

    public Activity c;
    public Dialog d;
    public Button yes, no;
    private Patient p1 = new Patient();
    private Appointment appt = new Appointment();

    public CancelApptDialog(Activity a,Patient p1, Appointment appt) {
        super(a);
        this.c = a;
        this.p1 = p1;
        this.appt = appt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_cancel_appt);

        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                DeleteBackground delete = new DeleteBackground(super.getContext(),p1,appt);
                delete.execute();
                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}