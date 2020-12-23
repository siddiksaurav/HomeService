package com.example.homeserviceapp.Model;

public class User {
    private String contactNo;
    private String location;
    private String email;
    private String name;
    private String password;
    private String url;


    public User() {

    }

    public User(String contactNo, String location, String email, String name, String password, String url) {
        this.contactNo = contactNo;
        this.location = location;
        this.email = email;
        this.name = name;
        this.password = password;
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
