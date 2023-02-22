package com.example.donationapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Donation implements Parcelable {

    // oop345

    String paymentMethod;
    double amount;
    boolean isSharable;
    int yearOfDonation;

    public Donation(String paymentMethod, double amount, boolean isSharable,int y) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.isSharable = isSharable;
        this.yearOfDonation = y;
    }

    protected Donation(Parcel in) {
        paymentMethod = in.readString();
        amount = in.readDouble();
        isSharable = in.readByte() != 0;
        yearOfDonation = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(paymentMethod);
        dest.writeDouble(amount);
        dest.writeByte((byte) (isSharable ? 1 : 0));
        dest.writeInt(yearOfDonation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Donation> CREATOR = new Creator<Donation>() {
        @Override
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        @Override
        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };

    String getReportMsg()
    {

        return (isSharable? "Thanks for your donation!! " + this.amount + "$ compldted via " + this.paymentMethod+" , and thanks for sharing Donation!!!"
: "Thanks for your donation!! " + this.amount + "$ compldted via " + this.paymentMethod);

    }

}
