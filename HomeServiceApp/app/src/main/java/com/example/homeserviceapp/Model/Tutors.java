package com.example.homeserviceapp.Model;

public class Tutors {
    private String name;
    private String rating;
    private String experience;
    private String Class;
    private String contactNo;
    private String divison;
    private String divison_class;
    private String subject;
    private String url;
    private String status;
    private String institution;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Tutors() {
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setclass(String Class) {
        Class = Class;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setDivison(String divison) {
        this.divison = divison;
    }

    public void setDivison_class(String divison_class) {
        this.divison_class = divison_class;
    }

    public void setSubject(String sub) {
        this.subject = sub;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getExperience() {
        return experience;
    }


    public String getclass() {
        return Class;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getDivison() {
        return divison;
    }

    public String getDivison_class() {
        return divison_class;
    }

    public String getSubject() {
        return subject;
    }

    public Tutors(String name, String rating, String experience, String aClass, String contactNo, String divison, String divison_class, String sub, String url, String institution, String status) {
        this.name = name;
        this.rating = rating;
        this.experience = experience;
        this.Class = aClass;
        this.contactNo = contactNo;
        this.divison = divison;
        this.divison_class = divison_class;
        this.subject = sub;
        this.url = url;
        this.status = status;
        this.institution = institution;

    }
}
