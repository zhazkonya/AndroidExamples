package com.example.zhaziraoskenbayeva.restexample;

/**
 * Created by zhaziraoskenbayeva on 03/10/17.
 */

/**
 * Created by z.oskenbaeva on 03.10.2017.
 */

public class Contact {

    String c_id ;
    String c_name ;
    String c_email ;
    String c_address;
    String c_gender ;
    String c_mobile ;
    String c_home ;
    String c_office ;

    public Contact(String id, String name, String email, String address, String gender, String mobile, String home, String office) {
        c_id = id;
        c_name = name ;
        c_email =  email ;
        c_address = address;
        c_gender = gender ;
        c_mobile = mobile ;
        c_home = home ;
        c_office = office ;
    }

    public String getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public String getC_email() {
        return c_email;
    }

    public String getC_address() {
        return c_address;
    }

    public String getC_gender() {
        return c_gender;
    }

    public String getC_mobile() {
        return c_mobile;
    }

    public String getC_home() {
        return c_home;
    }

    public String getC_office() {
        return c_office;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public void setC_gender(String c_gender) {
        this.c_gender = c_gender;
    }

    public void setC_mobile(String c_mobile) {
        this.c_mobile = c_mobile;
    }

    public void setC_home(String c_home) {
        this.c_home = c_home;
    }

    public void setC_office(String c_office) {
        this.c_office = c_office;
    }
}
