package com.example.serverhomeservice.Model;

public class Order {
    private String userid;
    private String providerid;
    private String ordertime;
    private String status;

    public Order() {
    }

    public Order(String userid, String providerid, String ordertime, String status) {
        this.userid = userid;
        this.providerid = providerid;
        this.ordertime = ordertime;
        this.status = status;
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
