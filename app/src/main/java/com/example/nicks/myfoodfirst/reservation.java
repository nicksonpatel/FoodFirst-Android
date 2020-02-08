package com.example.nicks.myfoodfirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.nicks.foodfirst.adpter.Subject;
import com.example.nicks.foodfirst.adpter.SubjectAdapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class reservation extends AppCompatActivity {

    ImageView back;
    RecyclerView recy;
    static SubjectAdapter2 adapter;
    static List<Subject> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), myhome.class);
                startActivity(i);
            }
        });
        recy = (RecyclerView) findViewById(R.id.recy);
        list = new ArrayList<>();
        adapter = new SubjectAdapter2(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Myapp.con);
        recy.setLayoutManager(mLayoutManager);
        recy.setItemAnimator(new DefaultItemAnimator());
        recy.setAdapter(adapter);


        getdata();
    }

    void getdata()
    {
        list.clear();
        adapter.notifyDataSetChanged();
        if(Myapp.userdata.containsKey("request")) {
            Map<String, Object> da=(Map<String, Object>)Myapp.userdata.get("request");
            List<String> allke=new ArrayList<>(da.keySet());
            for(int i=0;i<allke.size();i++)
            {
                Map<String,Object> dd=(Map<String, Object>) da.get(allke.get(i));
                list.add(new Subject(allke.get(i),dd));
            }
            adapter.notifyDataSetChanged();
        }
        else
        {
            Myapp.showError("No data found");
        }
    }

}
