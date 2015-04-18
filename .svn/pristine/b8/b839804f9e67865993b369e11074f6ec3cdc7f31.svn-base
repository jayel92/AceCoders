package com.example.jianlongguo.abs.Entities;

/**
 * Created by jianlongguo on 13/4/15.
 */
public class Appointment {

    public String clinic, id, nric,desc, referral, type, date, time;

    public Appointment(){}

    public Appointment(String clinic, String id, String nric, String desc, String date, String time, String referral,String type){
        this.clinic = clinic;
        this.id = id;
        this.nric = nric;
        this.type = type;
        this.date = date;
        this.time = time;
        this.desc = desc;
        this.referral = referral;
    }

    public String getClinic(){
        return this.clinic;
    }

    public String getId(){
        return this.id;
    }

    public String getNric() { return this.nric;}

    public String getDesc() {return this.desc;}

    public String getReferral() {return this.referral;}

    public String getType(){
        switch(this.type){
            case "0":
                return "New Appointment";
            case "1":
                return "Follow Up Appointment";
            case "2":
                return "Blood Test";
            case "3":
                return "X-ray/MRI";
        }
        return this.type;
    }

    public String getDate(){return this.date; }

    public String getTime(){ return this.time;  }

    public void setClinic(String clinic) {this.clinic = clinic;}

    public void setId(String id){this.id = id;}

    public void setNric(String nric) {this.nric = nric;}

    public void setDesc(String desc) {this.desc = desc;}
}
