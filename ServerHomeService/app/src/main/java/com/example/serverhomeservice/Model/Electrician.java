package com.example.serverhomeservice.Model;

public class Electrician {

    private String  name,rating,location,experience,contactNo,image,status,services,password;

    public Electrician() {
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Electrician(String name, String rating, String location, String experience, String contactNo, String image, String status, String services, String password) {
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.experience = experience;
        this.contactNo = contactNo;
        this.image = image;
        this.status = status;
        this.services = services;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getContact_no() {
        return contactNo;
    }

    public void setContact_no(String contact_no) {
        this.contactNo = contact_no;
    }
}
