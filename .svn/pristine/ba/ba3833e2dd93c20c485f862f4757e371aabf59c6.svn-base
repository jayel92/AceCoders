package com.example.jianlongguo.abs.Entities;

/**
 * Created by jianlongguo on 13/4/15.
 */
public class Appointment {

    public String clinic, type, date, time, location;

    public Appointment(){}

    public Appointment(String clinic, String type, String date, String time){
        this.clinic = clinic;
        this.type = type;
        this.date = date;
        this.time = time;
        this.location = null;
    }

    public String getClinic(){
        return this.clinic;
    }

    public String getLocation(){
        return this.location;
    }

    public String getType(){
        switch(this.type){
            case "1":
                return "New Appointment";
            case "2":
                return "Follow Up Appointment";
            case "3":
                return "Blood Test";
            case "4":
                return "X-ray/MRI";
        }
        return this.type;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public void setLocation(String loc){
        this.location = loc;
    }
}
