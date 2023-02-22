package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    String[] colleges = {"Seneca COllege", "Humber College","Centennial College"};
    ListView simpleList;
    ListView donationList;
    TextView msgText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
       // String sFromFirstActivity =  getIntent().getStringExtra("thanksMsg");

        Donation donationObjectFromMainActivity = getIntent().getParcelableExtra("newDonationObject");
        String sFromFirstActivity = donationObjectFromMainActivity.getReportMsg();

       // if (getIntent().hasExtra("listOfDonations"))
        ArrayList<Donation> listFromMainActivity =
                getIntent().getParcelableArrayListExtra("listOfDonations");


        msgText = findViewById(R.id.msg);
        msgText.setText(sFromFirstActivity);

        simpleList = findViewById(R.id.simpleList);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                R.layout.list_simple_row, R.id.simple_list_text, colleges);

        simpleList.setAdapter(listAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ReportActivity.this, " The selecte college is  " + colleges[i] , Toast.LENGTH_SHORT).show();
            }
        });

        donationList = findViewById(R.id.donalint_list);

        DonationBaseAdapter donationBaseAdapter =
                new DonationBaseAdapter(listFromMainActivity,this);
        donationList.setAdapter(donationBaseAdapter);


    }
}