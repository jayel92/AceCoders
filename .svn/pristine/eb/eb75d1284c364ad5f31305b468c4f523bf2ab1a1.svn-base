package com.example.jianlongguo.abs.Activities;

/**
 * Created by jianlongguo on 12/3/15.
 */

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class TimePickerFragment extends DialogFragment {

    OnTimeSetListener onTimeSet;

    public TimePickerFragment() {
    }

    public void setCallBack(OnTimeSetListener onTime) {
        onTimeSet = onTime;
    }

    private int hour, minute;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        hour = args.getInt("hour");
        minute = args.getInt("minute");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), onTimeSet, hour, minute, true);
    }
}
