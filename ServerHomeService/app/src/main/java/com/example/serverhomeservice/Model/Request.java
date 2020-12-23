package com.example.serverhomeservice.Model;

public class Request {
    private String ID;
    private String status;

    public Request() {
    }

    public Request(String ID, String status) {
        this.ID = ID;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
