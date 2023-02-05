package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    RadioButton payPal;
    RadioButton creditCard;
    EditText amoutText;
    CheckBox sharingBox;
    Button donateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        payPal =  findViewById(R.id.paypal);
        creditCard = findViewById(R.id.credit_card);
        amoutText = findViewById(R.id.donation_amount);
        sharingBox = findViewById(R.id.sharing_checkbox);
        donateButton = findViewById(R.id.donate_btn);
        donateButton.setOnClickListener(this);

//        donateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.donate_btn){
           if (isValid()){
               String pm = (payPal.isChecked())? "PayPal":"Credit Card";
                Donation newDonation = new Donation(pm,
                        Double.parseDouble(amoutText.getText().toString()),
                        sharingBox.isChecked());
               Toast.makeText(this, "Thanks for your donation!! " + newDonation.amount + " compldted via " + newDonation.paymentMethod , Toast.LENGTH_SHORT).show();
               // intent is masseging object
               Intent myIntent = new Intent(this, ReportActivity.class);
               // Extra = key value pair with this intent
               myIntent.putExtra("thanksMsg", newDonation.getReportMsg() );

               startActivity(myIntent);

           }else
               Toast.makeText(this, "Missing Info",Toast.LENGTH_LONG).show();
        }

    }


    boolean isValid(){
        boolean isValid = false;
            if (payPal.isChecked() || creditCard.isChecked()) {
                if (!amoutText.getText().toString().isEmpty())
                    isValid = true;
            }
            return isValid;
    }

}