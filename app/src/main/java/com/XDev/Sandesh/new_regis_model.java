package com.XDev.Sandesh;

public class new_regis_model {
    String fname, lname, pass, cpass, courses,  userid;
    new_regis_model(){

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public new_regis_model(String fname, String lname, String pass, String cpass, String courses, String userid) {
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.cpass = cpass;
        this.courses = courses;
        this.userid = userid;
    }
}
