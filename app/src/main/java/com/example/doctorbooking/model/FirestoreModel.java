package com.example.doctorbooking.model;

public class FirestoreModel {
    private String mobile,name,password;

    public FirestoreModel() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FirestoreModel(String mobile, String name, String password) {
        this.mobile = mobile;
        this.name = name;
        this.password = password;
    }
}
