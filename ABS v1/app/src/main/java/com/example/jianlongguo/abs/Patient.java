package com.example.jianlongguo.abs;

import java.util.Date;

/**
 * Created by jianlongguo on 31/3/15.
 */
public class Patient {

    //the patient has the following fields
    private String nric;
    private String password;
    private String name;
    private Date dateOfBirth;
    private int contact;
    private String email;
    private String address;
    private boolean comm;

    //constructor
    public Patient(String nric, String password, String name, Date dateOfBirth, int contact,
                   String email, String address, boolean comm) {
        this.nric = nric;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.comm = comm;
    }

    private void setNric(String nric){
        this.nric = nric;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setContact(int contact){
        this.contact = contact;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setComm(boolean comm){
        this.comm = comm;
    }


}
