package com.example.donationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DonationBaseAdapter extends BaseAdapter {


    ArrayList<Donation> donations_list;
    Context context;


    public DonationBaseAdapter(ArrayList<Donation> donations_list, Context context) {
        this.donations_list = donations_list;
        this.context = context;
    }


    @Override
    public int getCount() {// how many rows in the list (adapter view)
        return donations_list.size();
    }

    @Override
    public Donation getItem(int i) { // return Donation in specifc index
        return donations_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // build the view for each index in the table

        View v = LayoutInflater.from(context).inflate(R.layout.list_donation_row,viewGroup,false);

        TextView amount = v.findViewById(R.id.donation_list_AmoutText);
        TextView pmText = v.findViewById(R.id.donation_list_PMText);
        ImageView isSharedImage = v.findViewById(R.id.is_shared);

        amount.setText(donations_list.get(i).amount + "$");
        pmText.setText(donations_list.get(i).paymentMethod);

        if (donations_list.get(i).isSharable)
            isSharedImage.setImageResource(R.drawable.shared);
        else {
            isSharedImage.setImageResource(R.drawable.notshared);
        }


        return v;
    }
}
