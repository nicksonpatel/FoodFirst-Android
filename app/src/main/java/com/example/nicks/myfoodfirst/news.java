package com.example.nicks.myfoodfirst;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.nicks.foodfirst.adpter.PlanAdapter;
import com.example.nicks.foodfirst.adpter.plan_model;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class news extends AppCompatActivity {
    RecyclerView recy;
    PlanAdapter adapter;
    List<plan_model> list;
    ImageView back;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back=(ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             news.super.onBackPressed();
            }
        });

        pd=new ProgressDialog(news.this);
        pd.setCancelable(false);
        pd.setMessage("please wait");
        recy=(RecyclerView)findViewById(R.id.recy);
        list=new ArrayList<>();
        adapter=new PlanAdapter(list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Myapp.con);
        recy.setLayoutManager(mLayoutManager);
        recy.setItemAnimator(new DefaultItemAnimator());
        recy.setAdapter(adapter);
getData();
    }

    void getData()
    {
        Myapp.ref.child("system").child("plans").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String,String> data=(Map<String, String>) dataSnapshot.getValue();
                plan_model m=new plan_model(data.get("name"),data.get("price"),data.get("dec"),data.get("capacity"),data.get("eprice"));
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
