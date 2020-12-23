package com.example.homeserviceapp.Model;

public class Order {
    private String userid;
    private String providerid;
    private String ordertime;
    private String status;
    private String services;
    private String totalpayment;
    private String key;
    private  String rating;
    private String dueStatus;
    private String providerType;

    public Order() {
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public Order(String userid, String providerid, String ordertime, String status, String services, String totalpayment, String dueStatus, String key, String providerType,String rating) {
        this.userid = userid;
        this.providerType = providerType;
        this.key = key;
        this.providerid = providerid;
        this.ordertime = ordertime;
        this.status = status;
        this.rating=rating;
        this.services = services;
        this.totalpayment = totalpayment;
        this.dueStatus = dueStatus;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDueStatus() {
        return dueStatus;
    }

    public void setDueStatus(String dueStatus) {
        this.dueStatus = dueStatus;
    }

    public String getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(String totalpayment) {
        this.totalpayment = totalpayment;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }
}
