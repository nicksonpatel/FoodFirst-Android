package com.example.nicks.myfoodfirst;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class myhome extends AppCompatActivity {
    ProgressDialog pd;
    ImageView logo,logout,wallet,restaurents,searchbyfood,nearbyfood,news,reservation,streetfood,profile,settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhome);
        pd=new ProgressDialog(myhome.this);
        pd.setCancelable(false);
        pd.setMessage("please wait");


        restaurents=(ImageView) findViewById(R.id.restaurants);
        searchbyfood=(ImageView) findViewById(R.id.searchbyfood);
        nearbyfood=(ImageView) findViewById(R.id.nearbyfood);
        news=(ImageView) findViewById(R.id.news);
        reservation=(ImageView) findViewById(R.id.reservation);
        streetfood=(ImageView) findViewById(R.id.streetfood);
        profile=(ImageView) findViewById(R.id.profile);
        logout=(ImageView)findViewById(R.id.logout);
        wallet=(ImageView)findViewById(R.id.wallet);


        restaurents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),restaurents.class);
                i.putExtra("type","all");
                startActivity(i);
            }
        });
        searchbyfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),searchbyfood.class);
                startActivity(i);
            }
        });
        nearbyfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),nearbyfood.class);
                startActivity(i);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),news.class);
                startActivity(i);
            }
        });
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),reservation.class);
                startActivity(i);
            }
        });
        streetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i=new Intent(getApplicationContext(),MapsActivity.class);
                //startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),profile.class);
                startActivity(i);
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),wallet.class);
                startActivity(i);
            }
        });



    }



}
