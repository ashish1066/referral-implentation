package com.carselonadaily.carselonacustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import carselonadaily.carselonacustomer.R;
import theflyy.com.flyy.Flyy;
import theflyy.com.flyy.helpers.FlyyReferralCountFetchedListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        ImageView iconFAB = new ImageView(this);
        iconFAB.setImageResource(R.drawable.rewards);
        FloatingActionButton fab = findViewById(R.id.fab);
      //  fab.setImageResource(R.drawable.rewards);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flyy.navigateToOffersActivity(view.getContext());
            }
        });
    }

    public void navigateToOffers(View view){
        Flyy.navigateToOffersActivity(view.getContext());
    }
    public void navigateToHistory(View view){
        Flyy.navigateToReferralHistoryActivity(view.getContext());
    }
    public void showRererralCount(View view){
        Flyy.getReferralCount(this, new FlyyReferralCountFetchedListener() {
            @Override
            public void onReferralCountFetched(int referralsCount) {
                Toast.makeText(view.getContext(),""+referralsCount,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String failureMessage) {

            }
        });
    }
    public void navigateToVerifyReferral(View view){
        Intent intent = new Intent(view.getContext(),VerifyReferralActivity.class);
        startActivity(intent);
    }
    public void navigateToSetReferral(View view){
        Intent intent = new Intent(view.getContext(),SetReferralActivity.class);
        startActivity(intent);
    }
}