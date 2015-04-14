package com.example.jianlongguo.abs.Entities;

import java.util.Date;

/**
 * Created by jianlongguo on 11/4/15.
 */
public class Patient{

    String name, email, address, gender, mode, nric, password;
    Date dob;
    int contact;

    public Patient(){};

    public Patient(String nric, String password){
        this.nric = nric;
        this.password = password;
        this.email = this.address = this.gender = this.mode = this.name = null;
        this.dob = null;
        this.contact = 0;
    }

    public void setDetails(String name, String address, String gender, String mode, String email, Date dob, int contact){
        this.email = email;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.mode = mode;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getGender() {
        return this.gender;
    }

    public String getMode() {
        return this.mode;
    }

    public Date getDob(){
        return this.dob;
    }

    public int getContact() {
        return this.contact;
    }

    public String getNric() {
        return this.nric;
    }

    public String getPassword() {
        return this.password;
    }

    public void setNric(String nric){
        this.nric = nric;
    }

    public void setPassword(String password){
        this.password = password;
    }

}