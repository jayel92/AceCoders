package com.example.jianlongguo.abs.DB;

import com.example.jianlongguo.abs.Entities.Appointment;

/**
 * Created by Boonyh on 4/14/2015.
 */
public interface AsyncResponse {
    void processFinish(Appointment output);

}