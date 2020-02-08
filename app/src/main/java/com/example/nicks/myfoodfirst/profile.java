package com.example.nicks.myfoodfirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class profile extends AppCompatActivity {
    ImageView back,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back=(ImageView)findViewById(R.id.back);
        logout=(ImageView)findViewById(R.id.logout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),myhome.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor= Myapp.pref.edit();
                editor.clear();
                editor.commit();
                finish();
                Myapp.myname="";
                Myapp.mynumber="";
                Intent i=new Intent(getApplicationContext(),loginPage.class);
                startActivity(i);
                finish();
            }
        });



    }

}
