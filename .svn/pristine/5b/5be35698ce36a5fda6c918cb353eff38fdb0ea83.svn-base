package com.example.jianlongguo.abs.UI;

/**
 * Created by jianlongguo on 12/3/15.
 */

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    OnDateSetListener onDateSet;

    public DatePickerFragment() {
    }

    public void setCallBack(OnDateSetListener onDate) {
        onDateSet = onDate;
    }

    private int year, month, day;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("Year");
        month = args.getInt("Month");
        day = args.getInt("Day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date minDate = new Date();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), onDateSet, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTime());

        return datePickerDialog;
    }
}
