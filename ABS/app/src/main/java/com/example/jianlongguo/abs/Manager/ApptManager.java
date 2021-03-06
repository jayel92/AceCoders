package com.example.jianlongguo.abs.Manager;

import android.app.Activity;
import android.content.Context;

import com.example.jianlongguo.abs.DB.ChangeApptBackground;
import com.example.jianlongguo.abs.DB.DeleteBackground;
import com.example.jianlongguo.abs.DB.NewApptBackground;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jianlongguo on 18/4/15.
 */
public class ApptManager extends Activity implements DBManager{
    final static int WEEKSINADV = 8, MINWEEKS = 2, TOTALWEEKS = 52;
    public Appointment appt;
    public Calendar currDate = Calendar.getInstance();

    public ApptManager (){}

    public String checkValidDate(int apptDay, int apptMonth, int apptYear){
        String dateStr = (String.valueOf(apptDay) + "-" + String.valueOf(apptMonth+1) + "-" + String.valueOf(apptYear));
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;

        try {
            startDate = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = DateToCalendar(startDate);
        int curWeek = currDate.get(Calendar.WEEK_OF_YEAR);
        int curYear = currDate.get(Calendar.YEAR);
        int apptWeek = cal.get(Calendar.WEEK_OF_YEAR);

        //to allow appointments for at least 2 weeks in advance
        if ((curWeek + MINWEEKS < apptWeek && apptWeek <= curWeek + WEEKSINADV && curYear == apptYear)
                || (curYear < apptYear && TOTALWEEKS - curWeek + apptWeek < WEEKSINADV)) {
            return df.format(startDate);
        }
        else {
            return "-1";
        }
    }

    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    @Override
    public void add(Context c, String... arg0) {

    }

    @Override
    public void add(Context c, Patient p, String... arg0){
        new NewApptBackground(c,p).execute((String)arg0[0],(String)arg0[1],(String)arg0[2],
                (String)arg0[3],(String)arg0[4],(String)arg0[5],(String)arg0[6]);
    }

    @Override
    public void change(Context c, Patient p, String... arg0) {

    }

    @Override
    public void change(Context c, Patient p, Appointment a, String... arg0){
        new ChangeApptBackground(c,p,a).execute((String)arg0[0],(String)arg0[1],(String)arg0[2],
                (String)arg0[3],(String)arg0[4],(String)arg0[5]);
    }

    @Override
    public void delete(Context c, Patient p, Appointment a) {
        new DeleteBackground(c,p,a).execute();

    }





}


