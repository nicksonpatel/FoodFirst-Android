package com.example.nicks.myfoodfirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class searchbyfood extends AppCompatActivity {

    ImageView back;
    ImageView iv1,iv2,iv3,iv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyfood);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back=(ImageView)findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),myhome.class);
                startActivity(i);
            }
        });


        iv1=(ImageView)findViewById(R.id.appet);
        iv2=(ImageView)findViewById(R.id.breakfast);
        iv3=(ImageView)findViewById(R.id.coffee);
        iv4=(ImageView)findViewById(R.id.fish);



        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchbyfood.this,restaurents.class);
                i.putExtra("type","Fast-Food");
                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchbyfood.this,restaurents.class);
                i.putExtra("type","chinese");
                startActivity(i);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchbyfood.this,restaurents.class);
                i.putExtra("type","north indian");
                startActivity(i);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchbyfood.this,restaurents.class);
                i.putExtra("type","south indian");
                startActivity(i);
            }
        });
    }

}
