package com.example.nicks.myfoodfirst;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.nicks.myfoodfirst.adpter.Subject;
import com.example.nicks.myfoodfirst.adpter.SubjectAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class nearbyfood extends AppCompatActivity {

    ImageView back;
    Spinner spin;
    Button btn;
    RecyclerView recy;
    SubjectAdapter adapter;
    List<Subject> list;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearbyfood);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pd=new ProgressDialog(nearbyfood.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);

        back=(ImageView)findViewById(R.id.back);
        spin=(Spinner)findViewById(R.id.area);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(Myapp.con,android.R.layout.simple_spinner_dropdown_item,Myapp.area);
        spin.setAdapter(aa);
        btn=(Button)findViewById(R.id.btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),myhome.class);
                startActivity(i);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData(spin.getSelectedItem()+"");

            }
        });
        recy=(RecyclerView)findViewById(R.id.recy);
        list=new ArrayList<>();
        adapter=new SubjectAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Myapp.con);
        recy.setLayoutManager(mLayoutManager);
        recy.setItemAnimator(new DefaultItemAnimator());
        recy.setAdapter(adapter);
    }

    void getData(final String ty)
    {
        pd.show();
        list.clear();
        adapter.notifyDataSetChanged();
        Myapp.ref.child("res_area").child(ty).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Myapp.ref.child("res_area").child(ty).removeEventListener(this);
                pd.dismiss();
                if(dataSnapshot.getValue()==null)
                {
                    Myapp.showError("No data found");

                }
                else
                {
                    Map<String,Object> alldata=(Map<String, Object>)dataSnapshot.getValue();
                    List<String> allkeys=new ArrayList<>(alldata.keySet());

                    for(int i=0;i<allkeys.size();i++)
                    {
                        Map<String,Object> temp=(Map<String, Object>) alldata.get(allkeys.get(i));

                        list.add(new Subject(allkeys.get(i),temp));

                    }
                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
