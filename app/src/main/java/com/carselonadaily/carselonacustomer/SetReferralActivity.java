package com.carselonadaily.carselonacustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import carselonadaily.carselonacustomer.R;
import theflyy.com.flyy.Flyy;

public class SetReferralActivity extends AppCompatActivity {
    private EditText referralCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_referral);
        referralCode = (EditText) findViewById(R.id.edit_text_referral);
    }
    public void setReferral(View view){
        Flyy.setReferrerCode(referralCode.getText().toString());
    }
}