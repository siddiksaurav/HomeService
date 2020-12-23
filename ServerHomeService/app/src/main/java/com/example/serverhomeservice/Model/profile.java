package com.example.serverhomeservice.Model;

public class profile {
    String name,contactNo,nid,experience,password,location,status,image,rating,services;

    public profile(String name, String contact, String nid, String email, String password, String location, String status, String image,String services,String rating) {
        this.name = name;
        this.contactNo = contact;
        this.nid = nid;
        this.experience = email;
        this.password = password;
        this.location = location;
        this.status = status;
        this.image = image;
        this.services=services;
        this.rating=rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contactNo;
    }

    public void setContact(String contact) {
        this.contactNo = contact;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getEmail() {
        return experience;
    }

    public void setEmail(String email) {
        this.experience = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
