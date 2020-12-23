package com.example.serverhomeservice;

public class ServiceProvider {

    private String  name,rating,location,experience,contactNo,image,status,password;

    public ServiceProvider() {
    }

    public String getImage() {
        return image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ServiceProvider(String name, String rating, String location, String experience, String contactNo, String image, String status, String password) {
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.experience = experience;
        this.contactNo = contactNo;
        this.image = image;
        this.status = status;
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
