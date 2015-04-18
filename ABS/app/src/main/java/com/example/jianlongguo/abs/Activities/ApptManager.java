package com.example.jianlongguo.abs.Activities;

import android.app.Activity;
import android.widget.Toast;

import com.example.jianlongguo.abs.Entities.Appointment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jianlongguo on 18/4/15.
 */
public class ApptManager extends Activity{

    final static int WEEKSINADV = 8, MINWEEKS = 2, TOTALWEEKS = 52;
    public Appointment appt;
    public Calendar currDate = Calendar.getInstance();

    public ApptManager (){}

    public boolean checkValidDate(int apptDay, int apptMonth, int apptYear, int apptWeek){
        String dateStr = (String.valueOf(apptDay) + "-" + String.valueOf(apptMonth+1) + "-" + String.valueOf(apptYear));
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int curWeek = currDate.get(Calendar.WEEK_OF_YEAR);
        int curYear = currDate.get(Calendar.YEAR);
        Toast.makeText(getApplicationContext(), curWeek, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), curYear, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), apptDay + " " + apptMonth + " " + curYear + " " + curWeek, Toast.LENGTH_SHORT).show();

        //to allow appointments for at least 2 weeks in advance
        if ((curWeek + MINWEEKS < apptWeek && apptWeek <= curWeek + WEEKSINADV && curYear == apptYear)
                || (curYear < apptYear && TOTALWEEKS - curWeek + apptWeek < WEEKSINADV)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
