package com.example.jianlongguo.abs.UI;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jianlongguo.abs.Activities.R;
import com.example.jianlongguo.abs.DB.ForgetPassBackground;

/**
 * Created by jianlongguo on 15/4/15.
 */
public class ResetPasswordDialog extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button yes, no;
    TextView enter1;
    EditText userInput1;
    Button cancelBut, submitBut;
    public ResetPasswordDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_reset_password);

        cancelBut = (Button)findViewById(R.id.cancelBut);
        submitBut = (Button)findViewById(R.id.submitBut);
        userInput1 = (EditText)findViewById(R.id.userInput1);
        enter1 = (TextView)findViewById(R.id.enter1);

        cancelBut.setOnClickListener(this);
        submitBut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBut:
                dismiss();
                break;
            case R.id.submitBut:
                new ForgetPassBackground(this.getContext()).execute(userInput1.getText().toString());
                break;
            default:
                break;
        }
        dismiss();
    }

}
