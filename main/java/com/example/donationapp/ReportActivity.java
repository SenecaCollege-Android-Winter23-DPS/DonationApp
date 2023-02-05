package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {

    TextView msgText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        String sFromFirstActivity =  getIntent().getStringExtra("thanksMsg");

        msgText = findViewById(R.id.msg);
        msgText.setText(sFromFirstActivity);
    }
}