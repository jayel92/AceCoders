package com.example.jianlongguo.abs.Manager;

import android.content.Context;

import com.example.jianlongguo.abs.DB.RegisterBackground;
import com.example.jianlongguo.abs.Entities.Patient;

/**
 * Created by Boon Kok Chin on 18/4/2015.
 */
public class ProfileManager implements DBManager{
    @Override
    public void add(Context c, String... arg0) {
        new RegisterBackground(c).execute((String)arg0[0],(String)arg0[1],(String)arg0[2],
                (String)arg0[3],(String)arg0[4],(String)arg0[5],(String)arg0[6],
                (String)arg0[7],(String)arg0[8]);

    }

    @Override
    public void edit(Context c, Patient p, String... arg0) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void display() {

    }
}