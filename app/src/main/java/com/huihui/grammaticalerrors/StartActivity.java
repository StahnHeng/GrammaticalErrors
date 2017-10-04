package com.huihui.grammaticalerrors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class StartActivity extends AppCompatActivity {



    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        adRequest = new AdRequest.Builder().build();

    }


    public void lv1(View view) {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }


    public void lv2(View view) {
        Intent intent2 = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent2);

    }

    public void lv3(View view) {
        Intent intent3 = new Intent(getApplicationContext(),Main3Activity.class);
        startActivity(intent3);

    }
}
