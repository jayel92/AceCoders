package com.example.jianlongguo.abs.DB;

/**
 * Created by Boonyh on 4/13/2015.
 */
public class PatientTest {
    private String user;
    private String pwd;
    private String nameStr;
    private String emailStr;
    private String address;
    private String gender;
    private String phoneNoStr;
    private String mode;
    private String dobStr;
    PatientTest(String user,String pwd,String nameStr,String emailStr,String address,String gender,String phoneNoStr,String mode,String dobStr){
        this.user = user;
        this.pwd = pwd;
        this.nameStr = nameStr;
        this.emailStr = emailStr;
        this.address = address;
        this.gender = gender;
        this.phoneNoStr = phoneNoStr;
        this.mode = mode;
        this.dobStr = dobStr;
    }
    public String getNric() {
        return this.user;
    }

    public String getPassword() {
        return this.pwd;
    }

    public String getName() {
        return this.nameStr;
    }

    public String getEmail() {
        return this.emailStr;
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

    public String getDob(){
        return this.dobStr;
    }

    public String getContact() {
        return this.phoneNoStr;
    }

    public void setPassword(String password){
        this.pwd = password;
    }

    public void setEmail(String password){
        this.emailStr = password;
    }

    public void setAddress(String address){this.address = address; }

    public void setContact(String password){
        this.phoneNoStr = phoneNoStr;
    }

    public void setMOC(String mode){
        this.mode = mode;
    }
}
