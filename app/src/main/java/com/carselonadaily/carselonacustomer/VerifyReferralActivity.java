package com.carselonadaily.carselonacustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import carselonadaily.carselonacustomer.R;
import theflyy.com.flyy.Flyy;
import theflyy.com.flyy.helpers.FlyyVerifyReferralCode;

public class VerifyReferralActivity extends AppCompatActivity {
    private EditText referralCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_referral);
        referralCode = (EditText) findViewById(R.id.edit_text_verify);
    }

    public void verifyReferral(View view){
        Flyy.verifyReferralCode(this, "re", new FlyyVerifyReferralCode() {
            @Override
            public void isReferralCodeValid(boolean isValid, String referralCode) {
                if (isValid) {
                    Toast.makeText(view.getContext(),"Referral Code is valid",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(),"Referral Code is not valid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}