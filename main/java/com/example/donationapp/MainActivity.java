package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    RadioButton payPal;
    RadioButton creditCard;
    EditText amoutText;
    CheckBox sharingBox;
    Button donateButton;
    Spinner paymentSpinner;
    String[] methods = {"PayPal" , "Credit Card","Cash"};
    Button cameraButton;
   // static variable is not a good practice
    // static ArrayList<Donation> listOfDonations = new ArrayList<>(0);// queue == linked list
    // class varialbe
    // the good practice is to use Application Class.

    // Color selectedColor
    int numberOfClicks = 0;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Donation App","The activity will be destroied now");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Donation App","The activity will be created now");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            numberOfClicks = savedInstanceState.getInt("Clicks");


        payPal =  findViewById(R.id.paypal);
        cameraButton = findViewById(R.id.takeaphoto);
        creditCard = findViewById(R.id.credit_card);
        amoutText = findViewById(R.id.donation_amount);
        sharingBox = findViewById(R.id.sharing_checkbox);
        donateButton = findViewById(R.id.donate_btn);
        donateButton.setOnClickListener(this);
        cameraButton.setOnClickListener(this);
        paymentSpinner = findViewById(R.id.paymentMethod_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_row,
                R.id.spinner_pm_text,
                methods);

        paymentSpinner.setAdapter(adapter);
        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, " The selecte method is  " + methods[i] , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


//        donateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.takeaphoto){
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        }
        else
        if (view.getId() == R.id.donate_btn){
            numberOfClicks++;
            Log.d("Donation App", "You clicked on Donation Button "+numberOfClicks + " times");
           if (isValid()){
               String pm = (payPal.isChecked())? "PayPal":"Credit Card";
                Donation newDonation = new Donation(pm,
                        Double.parseDouble(amoutText.getText().toString()),
                        sharingBox.isChecked(), 2023);
               ((MyApp)getApplication()).listOfDonations.add(newDonation);
               Toast.makeText(this, "Thanks for your donation!! " + newDonation.amount + " compldted via " + newDonation.paymentMethod , Toast.LENGTH_SHORT).show();
                // int, char, byte, string, float, double, bool,
               // int[], char[]. byte[], string[]
               // intent is masseging object
               Intent myIntent = new Intent(this, ReportActivity.class);
               // Extra = key value pair with this intent
               //myIntent.putExtra("thanksMsg", newDonation.getReportMsg() );
               myIntent.putExtra("newDonationObject",newDonation);
               myIntent.putParcelableArrayListExtra("listOfDonations", ((MyApp)getApplication()).listOfDonations);
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


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Clicks",numberOfClicks);
    }
}