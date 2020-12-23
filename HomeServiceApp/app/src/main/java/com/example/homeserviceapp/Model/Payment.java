package com.example.homeserviceapp.Model;

public class Payment {
    private  String name;
    private String amount;

    public Payment(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public Payment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
