package com.example.donationapp;

public class Donation {

    String paymentMethod;
    double amount;
    boolean isSharable;

    public Donation(String paymentMethod, double amount, boolean isSharable) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.isSharable = isSharable;

    }

    String getReportMsg()
    {

        return (isSharable? "Thanks for your donation!! " + this.amount + "$ compldted via " + this.paymentMethod+" , and thanks for sharing Donation!!!"
: "Thanks for your donation!! " + this.amount + "$ compldted via " + this.paymentMethod);

    }

}
