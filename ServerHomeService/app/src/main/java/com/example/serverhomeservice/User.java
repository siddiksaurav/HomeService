package com.example.serverhomeservice;

public class User {
    private String name;
    private String password;
    private String location;
    private String contactNo;

    public User() {
    }

    public User(String name, String password, String location, String contactNo) {
        this.name = name;
        this.password = password;
        this.location = location;
        this.contactNo = contactNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getContactNo() {
        return contactNo;
    }
}
