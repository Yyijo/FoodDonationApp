package com.example.beathunger1;

public class User { String user_full_name;
    String user_age;
    String user_gender;
    String user_email;

    public User () {

    }

    public User(String user_email, String user_age, String user_gender) {
        this.user_email = user_email;
        this.user_age = user_age;
        this.user_gender = user_gender;

    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    }
